package icu.huajuan.feign;

import icu.huajuan.file.FileClient;
import icu.huajuan.model.file.dto.ImageInfoDTO;
import icu.huajuan.model.file.entity.ImageGallery;
import icu.huajuan.model.file.vo.ImageVo;
import icu.huajuan.service.QiNiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@RestController
@RequestMapping("/file")
public class FileClientController implements FileClient {

    @Autowired
    private QiNiuService qiNiuService;

    /**
     * 保存图片信息
     * @param imageInfoDTO 图片信息DTO
     */
    @PostMapping("/save")
    @Override
    public void saveImage(@RequestBody ImageInfoDTO imageInfoDTO) {
        qiNiuService.saveImage(imageInfoDTO);
    }

    @PostMapping("/get")
    @Override
    public List<ImageVo> getImage(@RequestParam("noteIds") List<Integer> noteIds) {
        return qiNiuService.getImage(noteIds);
    }
}
