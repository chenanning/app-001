package icu.huajuan.controller;

import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.files.dto.TaskInfoDTO;
import icu.huajuan.model.files.entity.SysUploadTask;
import icu.huajuan.model.files.param.InitTaskParam;
import icu.huajuan.service.SysUploadTaskService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/***
 *
 * @author Chen Anning
 **/
@RestController
@RequestMapping("/v1/minio/tasks")
public class SysOosController {
    /**
     * 服务对象
     */
    @Resource
    private SysUploadTaskService sysUploadTaskService;

    /**
     * 获取上传进度
     *
     * @param identifier 文件md5
     * @return
     */
    @GetMapping("/{identifier}")
    public ResponseResult<TaskInfoDTO> taskInfo(@PathVariable("identifier") String identifier) {
        return ResponseResult.okResult(sysUploadTaskService.getTaskInfo(identifier));
    }

    /**
     * 创建一个上传任务
     * <p>
     * BindingResult是Spring框架中的一个接口，用于表示数据绑定的结果。当Spring接收到一个Web请求时，会自动将请求参数绑定到对应的Java对象上。BindingResult接口用于捕获和报告在绑定过程中发生的错误。
     * BindingResult接口提供了检查是否存在错误、获取错误列表和添加新错误的方法。通常与@Valid注解一起使用，用于验证输入数据并向用户报告任何错误。
     * 总的来说，BindingResult接口是Spring框架数据绑定和验证过程中的重要组成部分，有助于确保用户输入得到正确的验证，并以清晰简洁的方式报告错误。
     *
     * @return
     */
    @PostMapping
    public ResponseResult<TaskInfoDTO> initTask(@Valid @RequestBody InitTaskParam param,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseResult.errorResult(500,"出错了");
        }
        return ResponseResult.okResult(sysUploadTaskService.initTask(param));
    }

    /**
     * 获取每个分片的预签名上传地址
     *
     * @param identifier 文件MD5
     * @param partNumber
     * @return
     */
    @GetMapping("/{identifier}/{partNumber}")
    public ResponseResult preSignUploadUrl(@PathVariable("identifier") String identifier,
                                           @PathVariable("partNumber") Integer partNumber) {
        SysUploadTask task = sysUploadTaskService.getByIdentifier(identifier);
        if (task == null) {
            return ResponseResult.errorResult(500,"分片任务不存在");
        }
        Map<String, String> params = new HashMap<>();
        params.put("partNumber", partNumber.toString());
        params.put("uploadId", task.getUploadId());
        return ResponseResult.okResult(sysUploadTaskService.genPreSignUploadUrl(task.getBucketName(), task.getObjectKey(), params));
    }

    /**
     * 合并分片
     *
     * @param identifier 文件MD5
     * @return
     */
    @PostMapping("/merge/{identifier}")
    public ResponseResult merge(@PathVariable("identifier") String identifier) {
        sysUploadTaskService.merge(identifier);
        return ResponseResult.okResult( "操作成功");
    }
}
