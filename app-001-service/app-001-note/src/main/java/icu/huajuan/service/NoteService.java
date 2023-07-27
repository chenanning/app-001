package icu.huajuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.note.dto.NoteDto;
import icu.huajuan.model.note.entity.Note;
import icu.huajuan.model.note.vo.NoteCartVo;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
public interface NoteService extends IService<Note> {

    List<NoteCartVo> load();

    /**
     * 保存笔记信息（包括图片信息）
     * @param noteDto 笔记信息
     * @return 执行结果
     */
    String saveNote(NoteDto noteDto);
}
