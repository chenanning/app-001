package icu.huajuan.feign;

import icu.huajuan.file.FileClient;
import icu.huajuan.model.file.dto.ImageInfoDTO;
import icu.huajuan.model.file.entity.ImageGallery;
import icu.huajuan.service.QiNiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @author Chen Anning
 **/
@RestController
@RequestMapping("/file")
public class FileClientController implements FileClient {

    @Autowired
    private QiNiuService qiNiuService;

    @PostMapping("/save")
    @Override
    public void saveImage(@RequestBody ImageInfoDTO imageInfoDTO) {
        qiNiuService.saveImage(imageInfoDTO);
    }
}
