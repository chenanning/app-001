package icu.huajuan.controller.v1;


import icu.huajuan.model.common.dto.Result;
import icu.huajuan.service.QiNiuService;
import icu.huajuan.user.IUserClient;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @author Chen Anning
 **/
@RestController
@RequestMapping("/qiniu")
public class QiNiuOssController {

    @Autowired
    private QiNiuService qiNiuService;

    /**
     * 获取上传凭证
     *
     * @return String
     */
    @GetMapping("/token")
    public Result<String> getUploadToken() {
        return Result.okResult(qiNiuService.getUploadToken());
    }



}
