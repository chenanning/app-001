package icu.huajuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.huajuan.model.note.entity.ImageGallery;
import icu.huajuan.model.note.vo.Cover;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@Mapper
public interface ImageGalleryMapper extends BaseMapper<ImageGallery> {

    Cover selectOneImageUrlByNoteId(@Param("id") Long id);
}
