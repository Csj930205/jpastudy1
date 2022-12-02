package com.jpa.jpastudy1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
public class BoardPageController {

    /*
    * 게시글 리스트 페이지
    * */
    @GetMapping("list")
    public String openBoardList() {
        return "board/list";
    }

    /*
    * 게시글 등록 페이지
    * */
    @GetMapping("write")
    public String openBoardWrite(@RequestParam(required = false) final Long seq, Model model) {
        model.addAttribute("seq", seq);
        return "board/write";
    }

    /*
    * 게시글 상세 페이지
    * */
    @GetMapping("/view/{seq}")
    public String openBoardView(@PathVariable final Long seq, Model model) {
        model.addAttribute("seq", seq);
        return "board/view";
    }
}
