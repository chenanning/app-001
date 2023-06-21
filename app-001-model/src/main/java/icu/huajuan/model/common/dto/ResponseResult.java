package icu.huajuan.model.common.dto;


import icu.huajuan.model.common.enums.AppHttpCodeEnum;
import lombok.Data;

import java.io.Serializable;

/***
 *
 * @author Chen Anning
 **/
@Data
public class ResponseResult<T> implements Serializable {

    private String host;

    // 返回状态码
    private Integer code;

    // 返回状态描述
    private String msg;

    // 返回对象类型
    private T data;

    private ResponseResult() {
        this.code = 200;
    }

    private ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 响应成功结果
     *
     * @param code 响应状态码
     * @param msg 响应消息
     * @return 结果
     */
    public static <T> ResponseResult<T> okResult(int code, String msg) {
        ResponseResult<T> result = new ResponseResult<>();
        return result.ok(code, null, msg);
    }

    /**
     *  响应失败结果
     *
     * @param code 响应状态码
     * @param msg 响应消息
     * @return 结果
     */
    public static <T> ResponseResult<T> errorResult(int code, String msg) {
        ResponseResult<T> result = new ResponseResult<>();
        return result.error(code, msg);
    }

    public static <T> ResponseResult<T> okResult(T data) {
        ResponseResult<T> result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getErrorMessage());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getErrorMessage());
    }

    public static <T> ResponseResult <T>  errorResult(AppHttpCodeEnum enums, String errorMessage){
        return setAppHttpCodeEnum(enums,errorMessage);
    }

    public static <T> ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getErrorMessage());
    }

    private static <T> ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums, String errorMessage){
        return okResult(enums.getCode(),errorMessage);
    }

    public ResponseResult<T> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResponseResult<T> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<T> ok(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        return this;
    }

    public ResponseResult<T> ok(T data) {
        this.data = data;
        return this;
    }
}
