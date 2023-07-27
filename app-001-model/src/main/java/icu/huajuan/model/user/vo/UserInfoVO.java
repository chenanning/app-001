package icu.huajuan.model.user.vo;

import lombok.Data;

@Data
public class UserInfoVO {
    /**
     * 用户id
     */
    Long id;

    /**
     * 用户名
     */
    String name;

    /**
     * 头像图片url
     */
    String image;
}
