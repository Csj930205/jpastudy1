package com.jpa.jpastudy1.controller;

import com.jpa.jpastudy1.domain.dto.BoardRequestDto;
import com.jpa.jpastudy1.domain.dto.BoardResponseDto;
import com.jpa.jpastudy1.paging.CommonParams;
import com.jpa.jpastudy1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    /*
    *  게시글 생성
    * */
    @PostMapping("boards")
    public Long save(@RequestBody final BoardRequestDto boardRequestDto) {
        return boardService.save(boardRequestDto);
    }

    /*
    *  게시글 리스트 조회
    * */
    @GetMapping("boards")
    public Map<String, Object> findAll(final CommonParams params) {
        return boardService.findAll(params);
    }

    /*
    * 게시글 수정
    * */
    @PatchMapping("/boards/{seq}")
    public Long update(@PathVariable("seq") final Long seq, @RequestBody final BoardRequestDto boardRequestDto) {
        return boardService.update(seq, boardRequestDto);
    }

    /*
    * 게시글 삭제
    * */
    @DeleteMapping("/boards/{seq}")
    public Long delete(@PathVariable("seq") final Long seq) {
        return boardService.delete(seq);
    }

    /*
    * 게시글 상세정보 조회
    * */
    @GetMapping("/boards/{seq}")
    public BoardResponseDto findById(@PathVariable("seq") Long seq) {
        return boardService.findById(seq);
    }

}
