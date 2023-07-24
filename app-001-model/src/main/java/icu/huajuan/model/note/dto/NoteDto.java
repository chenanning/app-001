package icu.huajuan.model.note.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoteDto {

    private Long id;

    // 用户id
    private Long userId;

    // 笔记类型（note or video）
    private String modelType;

    // 标题
    private String title;

    //
    private String type;
    // 内容
    private String desc;

    // 图片地址
    private String imageUrl;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

}