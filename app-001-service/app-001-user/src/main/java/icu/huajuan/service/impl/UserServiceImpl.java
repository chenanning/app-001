package icu.huajuan.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.huajuan.common.JwtUtil;
import icu.huajuan.mapper.UserMapper;
import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.common.enums.AppHttpCodeEnum;
import icu.huajuan.model.user.dto.LoginDto;
import icu.huajuan.model.user.entity.User;
import icu.huajuan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @author Chen Anning
 **/
@Service
@Transactional
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 登陆
     *
     * @param dto Dto
     * @return Map<String, Object>
     */
    @Override
    public ResponseResult<Map<String, Object>> login(LoginDto dto) {

        if (StringUtils.isNoneBlank(dto.getPhone()) && StringUtils.isNoneBlank(dto.getPassword())) {
            // 根据手机号查询用户信息
            User dbUser = getOne(Wrappers.<User>lambdaQuery().eq(User::getPhone, dto.getPhone()));
            if (dbUser == null) {
                ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户信息不存在");
            }
            // 对比密码
            assert dbUser != null;
            String salt = dbUser.getSalt();
            String password = dto.getPassword();
            String pswd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            if (!pswd.equals(dbUser.getPassword())) {
                ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

            // 生成token
            String token = JwtUtil.generateToken(dbUser.getId().longValue());
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            dbUser.setSalt("");
            dbUser.setPassword("");
            map.put("user", dbUser);

            return ResponseResult.okResult(map);
        } else {
            // 游客登录
            Map<String, Object> map = new HashMap<>();
            map.put("token", JwtUtil.generateToken(0L));
            return ResponseResult.okResult(map);
        }
    }
}
