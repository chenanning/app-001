package icu.huajuan.user;

import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.common.dto.Result;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @author Chen Anning
 **/
@FeignClient(value = "app-001-user",url = "localhost:51001")
@LoadBalancerClient(value = "app-001-user")
public interface IUserClient {

    @GetMapping("/api/v1/login/selectUserInfoById")
    public Result selectUserInfoById(String id);
}
