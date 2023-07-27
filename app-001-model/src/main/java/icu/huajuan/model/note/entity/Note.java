package icu.huajuan.model.note.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文章信息表，存储已发布的文章
 * </p>
 *
 * @author itheima
 */

@Data
@Accessors(chain = true)
@TableName("note")
public class Note implements Serializable {

    @TableId(value = "id")
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
    @TableField("`desc`")
    private String desc;

}