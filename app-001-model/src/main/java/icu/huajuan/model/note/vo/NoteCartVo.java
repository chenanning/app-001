package icu.huajuan.model.note.vo;

import icu.huajuan.model.user.entity.User;
import lombok.Data;

/***
 *
 * @author Chen Anning
 **/
@Data
public class NoteCartVo {
    // 图片信息 应该是一个对象
    private String cover;

    // 标题
    private String display_title;

    private String interact_info;
    // 类型
    private String type;

    // 用户信息 现在是entity 应该是一个vo
    private User user;
}
