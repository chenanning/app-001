package icu.huajuan.model.note.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Accessors(chain = true)
@TableName("note_like")
public class NoteLike {

    @TableId(value = "id")
    private Long id;

    /**
     * 业务id（笔记或评论）
     */
    @TableId(value = "type_id")
    private String  typeId;

    /**
     * 用户id
     */
    @TableId(value = "user_id")
    private String userId;

    /**
     * 类型(1:笔记,2:评论)
     */
    @TableId(value = "type")
    private Short type;

    /**
     *  点赞状态(1:点赞,2:取消点赞)
     */
    @TableId(value = "status")
    private Short status;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableId(value = "create_time")
    private DateTime createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableId(value = "update_time")
    private DateTime updateTime;
}
