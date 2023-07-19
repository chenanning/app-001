package icu.huajuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.common.dto.Result;
import icu.huajuan.model.user.dto.LoginDto;
import icu.huajuan.model.user.entity.User;

import java.util.Map;

/***
 *
 * @author Chen Anning
 **/
public interface UserService extends IService<User> {
    /**
     * 登陆
     *
     * @param dto Dto
     * @return Map<String, Object>
     */
    public ResponseResult<Map<String, Object>> login(LoginDto dto);

    public User selectUserInfoById(String userId);
}
