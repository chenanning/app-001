package icu.huajuan.controller.v1;

import icu.huajuan.model.common.dto.ResponseResult;
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


    @GetMapping("/homefeed")
    public ResponseResult<List<Note>> homefeed() {
        return ResponseResult.okResult(noteService.load());
    }

    @GetMapping("/feed")
    public ResponseResult<List<Note>> feed() {
        return ResponseResult.okResult(noteService.load());
    }


    ///  123    eesddwe    123+eesddwe


}
