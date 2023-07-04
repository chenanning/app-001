package icu.huajuan.controller.v1.v1;

import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.user.dto.LoginDto;
import icu.huajuan.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/***
 *
 * @author Chen Anning
 **/
@RestController
@RequestMapping("/api/v1/login")
public class UserLoginController {

    @Resource
    private UserService userService;

    @PostMapping("/login_auth")
    public ResponseResult<Map<String, Object>> login (@RequestBody LoginDto dto) {
        return userService.login(dto);
    }
}
