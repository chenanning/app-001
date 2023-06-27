package icu.huajuan.feign;

import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.user.IUserClient;
import org.springframework.web.bind.annotation.GetMapping;

/***
 *
 * @author Chen Anning
 **/
public class UserClient implements IUserClient {

    @GetMapping("/api/v1/user/select")
    @Override
    public ResponseResult selectUser() {
        return null;
    }
}
