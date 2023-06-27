package icu.huajuan.model.note.vo;

import lombok.Data;

/***
 *
 * @author Chen Anning
 **/
@Data
public class ItemVo {
    private Integer id;
    // note video
    private String model_type;
    private NoteCartVo note_cart;
    private String track_id;
}
