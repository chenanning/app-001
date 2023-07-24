package icu.huajuan.model.note.dto;

import lombok.Data;

import java.util.List;

@Data
public class NoteDto {

    // 用户id
    private String token;

    // 笔记类型（note or video）
    private String modelType;

    // 标题
    private String title;

    // 内容
    private String desc;

    // 图片地址
    private List<String> imageList;
}