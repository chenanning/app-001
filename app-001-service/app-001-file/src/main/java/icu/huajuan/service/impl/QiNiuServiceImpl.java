package icu.huajuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import icu.huajuan.mapper.QiNiuMapper;
import icu.huajuan.model.file.dto.ImageInfoDTO;
import icu.huajuan.model.file.entity.ImageGallery;
import icu.huajuan.model.file.vo.ImageVo;
import icu.huajuan.service.QiNiuService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@Slf4j
@Service
public class QiNiuServiceImpl extends ServiceImpl<QiNiuMapper, ImageGallery> implements QiNiuService {

    @Autowired
    private UploadManager uploadManager;

    @Autowired
    private BucketManager bucketManager;

    @Autowired
    private Auth auth;

    @Value("${qiniu.bucket}")
    public String bucket;

    @Value("${qiniu.hosts}")
    public String hosts;

    @Resource
    private QiNiuMapper qiNiuMapper;

    /**
     * 定义七牛云上传的相关策略
     */
    private StringMap putPolicy;
    /**
     * 获取上传凭证
     *
     * @return String
     */
    @Override
    public String getUploadToken() {
        return getToken();
    }

    /**
     * 以文件的形式上传
     * @param file 文件
     * @param fileName 文件名
     * @return String
     */
    @Override
    public String uploadFile(File file, String fileName) throws QiniuException {
        Response response  = this.uploadManager.put(file, fileName, getToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, fileName, getToken());
            retry++;
        }
        if (response.statusCode == 200) {
            return hosts + "/" + fileName;
        } else {
            log.error("七牛云上传文件失败，失败原因：{}", response.error);
            throw new QiniuException(response);
        }
    }

    /**
     * 以流的形式上传
     *
     * @param inputStream 文件流
     * @param fileName    文件名
     * @return String
     */
    @Override
    public String uploadFile(InputStream inputStream, String fileName) throws QiniuException {
        Response response = this.uploadManager.put(inputStream, fileName, getToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, fileName, getToken(), null, null);
            retry++;
        }
        if (response.statusCode == 200) {
            return hosts + "/" + fileName;
        }
        return "上传失败!";

    }


    /**
     * 删除文件
     *
     * @param key 文件名
     * @return String
     */
    @Override
    public String delete(String key) throws QiniuException {
        Response response = bucketManager.delete(this.bucket, key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(bucket, key);
        }
        return response.statusCode == 200 ? "删除成功!" : "删除失败!";
    }

    @Override
    public void saveImage(ImageInfoDTO imageInfoDTO) {
        ImageGallery imageGallery = new ImageGallery();
        imageGallery.setUserId(imageInfoDTO.getUserId());
        imageGallery.setNoteId(imageInfoDTO.getNoteId());
        imageGallery.setImageUrl(imageInfoDTO.getImageUrl());
        save(imageGallery);
    }

    @Override
    public List<ImageVo> getImage(List<Integer> noteIds) {
        return qiNiuMapper.getImage(noteIds);
    }


    /**
     * 获取上传凭证
     */
    public String getToken() {
        return this.auth.uploadToken(bucket, null, 3600, putPolicy);
    }

}
