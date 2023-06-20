package icu.huajuan.model.common;


import com.alibaba.fastjson2.JSON;
import icu.huajuan.model.common.enums.AppHttpCodeEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 *
 * @author Chen Anning
 **/
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    //
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
    public static ResponseResult okResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, null, msg);
    }

    /**
     *  响应失败结果
     *
     * @param code 响应状态码
     * @param msg 响应消息
     * @return 结果
     */
    public static ResponseResult errorResult(int code, String msg) {
        ResponseResult result = new ResponseResult();
        return result.error(code, msg);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getErrorMessage());
        if(data!=null) {
            result.setData(data);
        }
        return result;
    }

    public static ResponseResult errorResult(AppHttpCodeEnum enums){
        return setAppHttpCodeEnum(enums,enums.getErrorMessage());
    }

    public static ResponseResult  errorResult(AppHttpCodeEnum enums, String errorMessage){
        return setAppHttpCodeEnum(enums,errorMessage);
    }

    public ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum enums){
        return okResult(enums.getCode(),enums.getErrorMessage());
    }

    private static ResponseResult setAppHttpCodeEnum(AppHttpCodeEnum enums, String errorMessage){
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return msg;
    }

    public void setErrorMessage(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public static void main(String[] args) {
        //前置
        /*AppHttpCodeEnum success = AppHttpCodeEnum.SUCCESS;
        System.out.println(success.getCode());
        System.out.println(success.getErrorMessage());*/

        //查询一个对象
        /*Map map = new HashMap();
        map.put("name","zhangsan");
        map.put("age",18);
        ResponseResult result = ResponseResult.okResult(map);
        System.out.println(JSON.toJSONString(result));*/


        //新增，修改，删除  在项目中统一返回成功即可
        /*ResponseResult result = ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        System.out.println(JSON.toJSONString(result));*/


        //根据不用的业务返回不同的提示信息  比如：当前操作需要登录、参数错误
        /*ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN,"自定义提示信息");
        System.out.println(JSON.toJSONString(result));*/

        //查询分页信息
//        PageResponseResult responseResult = new PageResponseResult(1,5,50);
//        List list = new ArrayList();
//        list.add("itcast");
//        list.add("itheima");
//        responseResult.setData(list);
//        System.out.println(JSON.toJSONString(responseResult));
    }
}
