package icu.huajuan.controller.v1;

import icu.huajuan.model.common.dto.ResponseResult;
import icu.huajuan.model.note.dto.NoteDto;
import icu.huajuan.model.note.vo.NoteCartVo;
import icu.huajuan.service.NoteService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseResult<List<NoteCartVo>> homefeed() {
        return ResponseResult.okResult(noteService.load());
    }

//    @GetMapping("/feed")
//    public ResponseResult<List<Note>> feed() {
//        return ResponseResult.okResult(noteService.load());
//    }

    // 保存笔记信息（包括图片信息）
    @PostMapping("/save")
    public ResponseResult<String> saveNote(@RequestBody NoteDto noteDto, HttpServletRequest request) {
        String accessToken = request.getHeader("access_token");
        return ResponseResult.okResult(noteService.saveNote(noteDto, accessToken));
    }

}
