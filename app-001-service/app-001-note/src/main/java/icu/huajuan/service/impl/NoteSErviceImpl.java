package icu.huajuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.huajuan.mapper.NoteMapper;
import icu.huajuan.model.note.entity.Note;
import icu.huajuan.service.NoteService;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/***
 *
 * @author Chen Anning
 **/
@Slf4j
@Service
public class NoteSErviceImpl extends ServiceImpl<NoteMapper, Note> implements NoteService {

    @Resource
    private NoteMapper noteMapper;

    @Override
    public List<Note> load() {
        List<Note> load = noteMapper.load();
        System.out.println(load);
        return load;
    }
}
