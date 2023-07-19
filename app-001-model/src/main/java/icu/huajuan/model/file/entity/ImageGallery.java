package icu.huajuan.model.file.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/***
 *
 * @author Chen Anning
 **/
@Data
@Accessors(chain = true)
@TableName("image_gallery")
public class ImageGallery {

    private Long id;

    // 用户id
    private Long userId;

    // 笔记id
    private Long noteId;

    // 图片路径
    private String imageUrl;
}
