package icu.huajuan.controller.v1;

import icu.huajuan.file.FileClient;
import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.file.dto.ImageInfoDTO;
import icu.huajuan.model.note.entity.Note;
import icu.huajuan.service.NoteService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 *
 * @author Chen Anning
 **/
@RestController
@RequestMapping("api/sns/web/v1")
public class NoteController {

    @Resource
    private NoteService noteService;

    @Resource
    private FileClient fileClient;

    @GetMapping("/homefeed")
    public ResponseResult<List<Note>> homefeed() {
        return ResponseResult.okResult(noteService.load());
    }

    @GetMapping("/feed")
    public ResponseResult<List<Note>> feed() {
        return ResponseResult.okResult(noteService.load());
    }

    // 保存笔记信息（包括图片信息）
    @GetMapping("/save")
    public ResponseResult<String> save() {
        ImageInfoDTO imageInfoDTO = new ImageInfoDTO();
        imageInfoDTO.setImageUrl("https://www.baidu.com/img/bd_logo1.png");
        imageInfoDTO.setNoteId(2222L);
        imageInfoDTO.setUserId(3333L);
        fileClient.saveImage(imageInfoDTO);

        return ResponseResult.okResult("ok");
    }

}
