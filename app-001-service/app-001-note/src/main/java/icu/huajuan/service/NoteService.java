package icu.huajuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.note.dto.NoteDto;
import icu.huajuan.model.note.entity.Note;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
public interface NoteService extends IService<Note> {

    List<Note> load();
}
