package icu.huajuan.feign;

import icu.huajuan.model.common.dto.Result;
import icu.huajuan.service.UserService;
import icu.huajuan.user.IUserClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @author Chen Anning
 **/
@RestController
@RequestMapping("/userFeign")
public class UserClientController implements IUserClient {

    @Resource
    UserService userService;

    @GetMapping("/selectUserInfoById")
    @Override
    public Result selectUserInfoById(@RequestParam String id) {
        return Result.okResult(userService.selectUserInfoById(id));
    }
}
