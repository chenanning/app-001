package icu.huajuan.user;

import icu.huajuan.model.common.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/***
 *
 * @author Chen Anning
 **/
@FeignClient("app-001-user")
public interface IUserClient {

    @GetMapping("/api/v1/user/select")
    public ResponseResult selectUser();
}
