package icu.huajuan.model.common.enums;

/***
 *
 * @author Chen Anning
 **/
public enum ResultEnum {

    FAIL(500, "失败"),
    SUCCESS(200000, "成功");

    private final Integer code;
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
