package icu.huajuan.controller.v1;

import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.common.dto.Result;
import icu.huajuan.model.user.dto.LoginDto;
import icu.huajuan.service.UserService;
import icu.huajuan.user.IUserClient;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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

    @Resource
    IUserClient iUserClient;

    @Resource
    RedisTemplate redisTemplate;

    @PostMapping("/login_auth")
    public ResponseResult<Map<String, Object>> login (@RequestBody LoginDto dto) {
        return userService.login(dto);
    }


    @GetMapping("/testRedis")
    public Result testRedis () {
        redisTemplate.opsForValue().set("testKey","testValue");
         return Result.okResult(redisTemplate.opsForValue().get("testKey"));
    }

    @GetMapping("/selectUserInfoById")
    public Result selectUserInfoById (@RequestParam String id) {
//        redisTemplate.opsForValue().set("testKey","testValue");
//        return Result.okResult(userService.selectUserInfoById(id));
        return Result.okResult(iUserClient.selectUserInfoById(id));
    }

}
