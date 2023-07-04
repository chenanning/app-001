package icu.huajuan.model.files.dto;

import cn.hutool.core.convert.Convert;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/***
 * 前端直传 DTO 类
 * @author Chen Anning
 **/
@Data
@ToString
@Accessors(chain = true)
public class TokenAndUrlDTO extends Convert {

    // 上传token
    private String token;

    // 上传地址
    private String hosts;
}
