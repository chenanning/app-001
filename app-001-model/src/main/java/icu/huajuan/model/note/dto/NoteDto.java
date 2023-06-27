package icu.huajuan.model.note.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoteDto {

    private String category;

    private String cursor_score;

    private Integer note_index;

    private Integer num;
    // 刷新类型
    private Integer refresh_type;

    private String search_key;

    private String unread_begin_note_id;

    private String unread_end_note_id;

    private Integer unread_note_count;

}