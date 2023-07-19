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

    /**
     * 保存前端传过来的图片地址和信息
     * eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzeXN0ZW0iLCJpc3MiOiJhcHAtMDAxIiwiYXVkIjoiYXBwIiwiaWF0IjoxNjg5NTc2MjE2LCJleHAiOjE2ODk2NjI2MTYsImlkIjoxfQ.BocpXovzFksV6PBE4NsmfraZujvdK5GDnpYrELdpDfk
     */
    @PostMapping("/save")
    public Result<String> saveImage(@RequestBody ImageInfoDTO imageInfoDTO) {
        qiNiuService.saveImage(imageInfoDTO);
        return Result.okResult("保存成功");
    }


}
