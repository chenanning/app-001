package icu.huajuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qiniu.common.QiniuException;
import icu.huajuan.model.file.dto.ImageInfoDTO;
import icu.huajuan.model.file.dto.TokenAndUrlDTO;
import icu.huajuan.model.file.entity.ImageGallery;

import java.io.File;
import java.io.InputStream;

/***
 *
 * @author Chen Anning
 **/
public interface QiNiuService extends IService<ImageGallery> {

    /**
     * 获取上传凭证
     * @return String
     */
    String getUploadToken();

    /**
     * 以文件的形式上传
     * @param file 文件
     * @param fileName 文件名
     * @return String
     */
    String uploadFile(File file, String fileName) throws QiniuException;
    /**
     * 以流的形式上传
     * @param inputStream 文件流
     * @param fileName 文件名
     * @return String
     */
    String uploadFile(InputStream inputStream, String fileName) throws QiniuException;

    /**
     * 删除文件
     * @param key 文件名
     * @return String
     */
    String delete(String key) throws QiniuException;


    void saveImage(ImageInfoDTO imageInfoDTO);
}
