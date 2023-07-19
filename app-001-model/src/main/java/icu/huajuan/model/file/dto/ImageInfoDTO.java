package icu.huajuan.model.file.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/***
 *
 * @author Chen Anning
 **/

@Data
@ToString
@Accessors(chain = true)
public class ImageInfoDTO {

    /**
     * 用户token
     */
    private Long userId;

    /**
     * 笔记id
     */
    private Long noteId;

    /**
     * 图片地址
     */
    private String imageUrl;
}
