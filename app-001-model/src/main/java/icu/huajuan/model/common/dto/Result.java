package icu.huajuan.model.common.dto;

import java.io.Serializable;

/***
 * 响应结果类
 * @author Chen Anning
 **/
public class Result<T> implements Serializable {
    private String host;
    // 返回状态码
    private Integer code;
    // 返回状态描述
    private String msg;
    // 返回数据
    private T data;

    public Result() {
        this.code = 200;
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> okResult(int code, String msg) {
        Result<T> result = new Result<>();
        return result.ok(code, null, msg);
    }

    public static <T> Result<T> okResult(T data) {
        Result<T> result = new Result<>();
        return result.ok(data);
    }

    public static <T> Result<T> okResult() {
        Result<T> result = new Result<>();
        return result.ok();
    }

    public static <T> Result<T> errorResult(int code, String msg) {
        Result<T> result = new Result<>();
        return result.error(code, msg);
    }

    public static <T> Result<T> errorResult(String msg) {
        Result<T> result = new Result<>();
        return result.error(msg);
    }

    public static <T> Result<T> errorResult() {
        Result<T> result = new Result<>();
        return result.error();
    }

    public Result<T> ok(int code, T data, String msg) {
        this.setCode(code);
        this.setData(data);
        this.setMsg(msg);
        return this;
    }

    public Result<T> ok(T data) {
        this.setCode(200);
        this.setData(data);
        this.setMsg("操作成功");
        return this;
    }

    public Result<T> ok() {
        this.setCode(200);
        this.setMsg("操作成功");
        return this;
    }

    public Result<T> error(int code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }

    public Result<T> error(String msg) {
        this.setCode(500);
        this.setMsg(msg);
        return this;
    }

    public Result<T> error() {
        this.setCode(500);
        this.setMsg("操作失败");
        return this;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{host='" + this.host + '\'' + ", code=" + this.code + ", msg='" + this.msg + '\'' + ", data=" + this.data + '}';
    }
}
