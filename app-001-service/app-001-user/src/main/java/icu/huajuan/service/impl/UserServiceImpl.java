package icu.huajuan.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.huajuan.common.JwtUtil;
import icu.huajuan.constant.UserConstant;
import icu.huajuan.mapper.UserMapper;
import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.common.dto.Result;
import icu.huajuan.model.common.enums.AppHttpCodeEnum;
import icu.huajuan.model.user.dto.LoginDto;
import icu.huajuan.model.user.dto.RegisterDTO;
import icu.huajuan.model.user.dto.SelectUserIdsDTO;
import icu.huajuan.model.user.entity.User;
import icu.huajuan.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/***
 *
 * @author Chen Anning
 **/
@Service
@Transactional
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    UserMapper userMapper;
    /**
     * 登陆
     *
     * @param dto Dto
     * @return Map<String, Object>
     */
    @Override
    public Result<Map<String, Object>> login(LoginDto dto) {

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

            boolean flag =  verify(password,dbUser.getPassword());
//            String pswd = DigestUtils.md5DigestAsHex((password + salt).getBytes());
            if (!flag) {
                ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }

            // 生成token
            String token = JwtUtil.generateToken(dbUser.getId().longValue());

            redisTemplate.opsForValue().set(UserConstant.userTokenPrefix+dbUser.getId(),token);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            dbUser.setSalt("");
            dbUser.setPassword("");
            map.put("user", dbUser);

            return Result.okResult(map);
        } else {
            // 游客登录
            Map<String, Object> map = new HashMap<>();
            map.put("token", JwtUtil.generateToken(0L));
            return Result.okResult(map);
        }
    }

    @Override
    public Result register(RegisterDTO dto) {
        Result result = new Result();
        String phone = dto.getPhone();
        String username = dto.getUserName();
        String password = dto.getPassword();
        Short sex = dto.getSex();
        //未设置用户名则用手机号填补
        if (StringUtils.isBlank(username)){
            dto.setUserName(phone);
        }
        if (null == sex){
            dto.setSex(Short.valueOf("2"));
        }

        Long phoneCount = userMapper.selectCount(new QueryWrapper<User>().eq("phone",phone));
        if (phoneCount.intValue()>0){
            return  result.error("手机号已注册");
        }

        try {
            User user = new User();
            user.setName(username);
            user.setPhone(phone);
            user.setCreatedTime(DateTime.now());

            Map<String,String> saltMap = generate(password);
            user.setSalt(saltMap.get("salt"));
            user.setPassword(saltMap.get("newPassword"));

            userMapper.insert(user);
            result.ok().setMsg("注册成功");
        }catch (Exception e){
            result.error("注册失败");
        }

        return result;
    }

    @Override
    public List<User> selectUserInfoByIds(SelectUserIdsDTO dto) {
        return userMapper.selectBatchIds(dto.getIds());
    }

    @Override
    public List<User> selectUserInfoByIds(List<Integer> ids) {
        return userMapper.selectBatchIds(ids);
    }


    //生成普通的MD5码
    public static String MD5(String input) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));

        }
        return hexValue.toString();
    }

    //生成“盐”和加盐后的MD5码，并将盐混入到MD5码中
    public static Map generate(String password) {
        Map map = new HashMap();
        //生成一个16位的随机数，也就是所谓的“盐”
        Random r = new Random();
        StringBuilder sb = new StringBuilder(16);
        sb.append(r.nextInt(99999999)).append(r.nextInt(99999999));
        int len = sb.length();
        if (len < 16) {
            for (int i = 0; i < 16 - len; i++) {
                sb.append("0");
            }
        }
        String salt = sb.toString();
        map.put("salt",salt);
        //将“盐”加到明文中，并生成新的MD5码
        password = md5Hex(password + salt);
        //将“盐”混到新生成的MD5码中，之所以这样做是为了后期更方便的校验明文和秘文，也可以不用这么做，不过要将“盐”单独存下来，不推荐这种方式
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        map.put("newPassword",new String(cs));
        return map;
    }

    //验证明文和加盐后的MD5码是否匹配
    public static boolean verify(String password, String md5) {
        //先从MD5码中取出之前加的“盐”和加“盐”后生成的MD5码
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        //比较二者是否相同
        return md5Hex(password + salt).equals(new String(cs1));
    }

    //生成MD5
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

}
