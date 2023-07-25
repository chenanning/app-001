package icu.huajuan.model.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterDTO {
    /**
     * 手机号
     */
    @NotBlank
    @Pattern(regexp="^1(3|4|5|7|8)\\d{9}$",message="手机号码格式错误！")
    String phone;

    /**
     * 密码
     */
    @NotBlank
    String password;

    /**
     * 用户名
     */
    String userName;

    /**
     * 头像
     */
    String image;

    /**
     * 邮箱
     */
    String email;

    /**
     * 性别
     */
    Short sex;



}
