package icu.huajuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.huajuan.model.files.dto.TaskInfoDTO;
import icu.huajuan.model.files.entity.SysUploadTask;
import icu.huajuan.model.files.param.InitTaskParam;

import java.util.Map;

/***
 *
 * @author Chen Anning
 **/
public interface SysUploadTaskService extends IService<SysUploadTask> {
    /**
     * 初始化一个任务
     *
     * @param param
     * @return
     */
    TaskInfoDTO initTask(InitTaskParam param);

    /**
     * 获取文件地址
     *
     * @param bucket
     * @param objectKey
     * @return
     */
    String getPath(String bucket, String objectKey);


    /**
     * 获取上传进度
     *
     * @param identifier
     * @return
     */
    TaskInfoDTO getTaskInfo(String identifier);

    /**
     * 根据md5标识获取分片上传任务
     *
     * @param identifier
     * @return
     */
    SysUploadTask getByIdentifier(String identifier);

    /**
     * 生成预签名上传url
     *
     * @param bucket    桶名
     * @param objectKey 对象的key
     * @param params    额外的参数
     * @return
     */
    String genPreSignUploadUrl(String bucket, String objectKey, Map<String, String> params);

    /**
     * 合并分片
     *
     * @param identifier 文件MD5
     */
    void merge(String identifier);
}
