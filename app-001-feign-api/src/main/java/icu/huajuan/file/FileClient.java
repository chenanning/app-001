package icu.huajuan.file;

import icu.huajuan.model.file.dto.ImageInfoDTO;
import icu.huajuan.model.file.vo.ImageVo;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@FeignClient(value = "app-001-file")
@LoadBalancerClient("app-001-file")
public interface FileClient {

    // 根据用户id和笔记id获取图片信息
    @PostMapping("/file/save")
    void saveImage(ImageInfoDTO imageInfoDTO);

    // 根据笔记id获取图片信息
    @PostMapping("/file/get")
    List<ImageVo> getImage(List<Integer> noteIds);
}
