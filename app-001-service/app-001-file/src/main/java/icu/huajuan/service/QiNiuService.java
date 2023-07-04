package icu.huajuan.service;

import com.qiniu.common.QiniuException;
import icu.huajuan.model.files.dto.TokenAndUrlDTO;

import java.io.File;
import java.io.InputStream;

/***
 *
 * @author Chen Anning
 **/
public interface QiNiuService {

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



}
