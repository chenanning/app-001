package icu.huajuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.huajuan.model.file.entity.ImageGallery;
import icu.huajuan.model.file.vo.ImageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@Mapper
public interface QiNiuMapper extends BaseMapper<ImageGallery> {

    List<ImageVo> getImage(@Param("noteId") Long noteId);
}
