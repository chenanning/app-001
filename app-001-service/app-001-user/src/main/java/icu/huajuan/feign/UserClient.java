package icu.huajuan.feign;

import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.common.dto.Result;
import icu.huajuan.service.UserService;
import icu.huajuan.user.IUserClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;

/***
 *
 * @author Chen Anning
 **/
public class UserClient implements IUserClient {

    @Resource
    UserService userService;

    @GetMapping("selectUserInfoById")
    @Override
    public Result selectUserInfoById(String id) {
        return Result.okResult(userService.selectUserInfoById(id));
    }
}
