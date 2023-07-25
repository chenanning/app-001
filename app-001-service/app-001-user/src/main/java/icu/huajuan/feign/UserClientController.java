package icu.huajuan.feign;

import icu.huajuan.model.common.dto.Result;
import icu.huajuan.model.user.dto.SelectUserIdsDTO;
import icu.huajuan.service.UserService;
import icu.huajuan.user.IUserClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/***
 *
 * @author Chen Anning
 **/
@RestController
@RequestMapping("/userFeign")
public class UserClientController implements IUserClient {

    @Resource
    UserService userService;

    @PostMapping("/selectUserInfoByIds")
    @Override
    public Result selectUserInfoByIds(@RequestBody SelectUserIdsDTO dto) {
        return Result.okResult(userService.selectUserInfoByIds(dto));
    }
}
