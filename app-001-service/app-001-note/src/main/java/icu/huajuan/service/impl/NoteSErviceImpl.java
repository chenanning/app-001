package icu.huajuan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.huajuan.mapper.NoteMapper;
import icu.huajuan.model.note.entity.Note;
import icu.huajuan.model.note.vo.NoteCartVo;
import icu.huajuan.service.NoteService;

import icu.huajuan.user.IUserClient;
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

    @Resource
    private IUserClient userClient;

    @Override
    public List<Note> load() {
        List<Note> load = noteMapper.load();
        System.out.println(load);
        userClient.selectUser();
        return load;
    }
}
