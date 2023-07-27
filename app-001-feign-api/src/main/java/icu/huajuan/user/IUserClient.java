package icu.huajuan.user;

import icu.huajuan.model.common.dto.Result;
import icu.huajuan.model.user.dto.SelectUserIdsDTO;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@FeignClient(value = "app-001-user")
@LoadBalancerClient(value = "app-001-user")
public interface IUserClient {

    @PostMapping("/userFeign/selectUserInfoByIds")
    Result selectUserInfoByIds(@RequestBody List<Integer> ids);
}
