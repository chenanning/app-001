package icu.huajuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.huajuan.model.common.dto.Result;
import icu.huajuan.model.user.dto.LoginDto;
import icu.huajuan.model.user.dto.RegisterDTO;
import icu.huajuan.model.user.dto.SelectUserIdsDTO;
import icu.huajuan.model.user.entity.User;

import java.util.List;
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
     Result<Map<String, Object>> login(LoginDto dto);


     Result register(RegisterDTO dto);


     List<User> selectUserInfoByIds(SelectUserIdsDTO dto);

     List<User> selectUserInfoByIds(List<Integer> ids);
}
