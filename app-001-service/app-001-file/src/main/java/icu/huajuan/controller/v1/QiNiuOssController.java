package icu.huajuan.controller.v1;


import icu.huajuan.model.common.dto.Result;
import icu.huajuan.model.file.dto.ImageInfoDTO;
import icu.huajuan.service.QiNiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
