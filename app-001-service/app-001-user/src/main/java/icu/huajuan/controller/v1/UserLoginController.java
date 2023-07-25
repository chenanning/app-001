package icu.huajuan.controller.v1;

import icu.huajuan.constant.UserConstant;
import icu.huajuan.model.common.dto.Result;
import icu.huajuan.model.user.dto.LoginDto;
import icu.huajuan.model.user.dto.RegisterDTO;
import icu.huajuan.model.user.dto.SelectUserIdsDTO;
import icu.huajuan.model.user.entity.User;
import icu.huajuan.service.UserService;
import icu.huajuan.user.IUserClient;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
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
    RedisTemplate<Object,Object> redisTemplate;

    /**
     * 登录
     * @param dto
     * @return
     */
    @PostMapping("/login_auth")
    public Result<Map<String, Object>> login (@RequestBody LoginDto dto) {
        return userService.login(dto);
    }

    /**
     * 注册
     * @param dto
     * @return
     */
    @PostMapping("/register")
    public Result<User> register (@RequestBody @Validated RegisterDTO dto) {
        return userService.register(dto);
    }


    @GetMapping("/testRedis")
    public Result testRedis () {
        redisTemplate.opsForValue().set("testKey","testValue");
        redisTemplate.opsForValue().set(UserConstant.userTokenPrefix+"id","token");
        return Result.okResult(redisTemplate.opsForValue().get("testKey"));
    }

    /**
     * 根据id批量查找用户信息
     * @param dto
     * @return
     */
    @PostMapping("/selectUserInfoByIds")
    public Result selectUserInfoById (@RequestBody SelectUserIdsDTO dto) {
        return Result.okResult(userService.selectUserInfoByIds(dto));
    }

}
