package icu.huajuan.service.impl;

import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.huajuan.common.JwtUtil;
import icu.huajuan.mapper.ImageGalleryMapper;
import icu.huajuan.mapper.NoteMapper;
import icu.huajuan.model.note.dto.NoteDto;
import icu.huajuan.model.note.entity.ImageGallery;
import icu.huajuan.model.note.entity.Note;
import icu.huajuan.model.note.vo.Cover;
import icu.huajuan.model.note.vo.NoteCartVo;
import icu.huajuan.service.NoteService;

import icu.huajuan.user.IUserClient;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ImageGalleryMapper imageGalleryMapper;

    @Resource
    private IUserClient userClient;


    @Override
    public List<NoteCartVo> load() {
        // vo
        List<NoteCartVo> load = noteMapper.load();
//        load.forEach(noteCartVo -> {
//            // 图片
//            Cover images = imageGalleryMapper.selectOneImageUrlByNoteId(noteCartVo.getId());
//            noteCartVo.setCover(images);
//        });
        return load;
    }

    /**
     * 保存笔记信息（包括图片信息）
     *
     * @param noteDto 笔记信息
     * @return 执行结果
     */
    @Transactional
    @Override
    public String saveNote(NoteDto noteDto, String accessToken) {
        Long userId = JwtUtil.getIdFromToken(accessToken);
        // 保存笔记信息
        Note note = new Note();
        note.setUserId(userId);
        note.setTitle(noteDto.getTitle());
        note.setDesc(noteDto.getDesc());
        System.out.println(noteDto.getDesc());
        note.setModelType(noteDto.getModelType());
        save(note);
        // 保存图片信息
        List<String> imageList = noteDto.getImageList();
        for (String image : imageList) {
            ImageGallery imageGallery = new ImageGallery();
            imageGallery.setNoteId(note.getId());
            imageGallery.setImageUrl(image);
            imageGallery.setUserId(userId);
            imageGalleryMapper.insert(imageGallery);
        }
        return "保存成功";
    }
}
