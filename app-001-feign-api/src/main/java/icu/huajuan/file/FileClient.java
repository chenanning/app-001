package icu.huajuan.file;

import icu.huajuan.model.file.dto.ImageInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;

/***
 *
 * @author Chen Anning
 **/
@FeignClient("app-001-file")
public interface FileClient {

    // 根据用户id和笔记id获取图片信息
    void saveImage(ImageInfoDTO imageInfoDTO);
}
