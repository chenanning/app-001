package icu.huajuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.huajuan.model.note.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@Mapper
public interface NoteMapper extends BaseMapper<Note> {

    List<Note> load();
}
