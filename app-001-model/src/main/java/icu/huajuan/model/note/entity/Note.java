package icu.huajuan.model.note.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
@TableName("note")
public class Note implements Serializable {

    @TableId(value = "id")
    private Long id;

    private String user_id;


    private String model_type;

    /**
     * 标题
     */
    private String title;

    private String type;

    private String desc;

}