package icu.huajuan.model.note.vo;

import icu.huajuan.model.user.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@Data
@Accessors(chain = true)
public class NoteCartVo {
    // 笔记id
    private Long id;
    // 标题
    private String title;
    // 互动信息(点赞数等) 应该是一个对象
    private String interact_info;
    // 类型
    private String modelType;
    // 描述
    private String desc;
    // 图片信息
    private String imageId;
    // 图片url
    private String imageUrl;
    // 用户信息 现在是entity 应该是一个vo
    private User user;
}
