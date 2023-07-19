package com.jpastudy.board.controller;

import com.jpastudy.board.Service.BoardService;
import com.jpastudy.board.domain.Board;
import com.jpastudy.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.TypeCache;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;


    //전체 게시물
    //페이징 page: 디폴트 페이지, size : 한 페이지 게시글 수, sort : 정렬기준칼럼,direction: 정렬 순서
    @GetMapping
    public String getAllBoards(
            ModelMap modelMap,
            @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable
    ){
        Page<BoardDto> boards = boardService.getAllBoard(pageable);


        modelMap.addAttribute("boards", boards);

        return "board";
    }

    //게시물 상세 페이지(게시글 단건 조회)
    @GetMapping("/{boardId}")
    public String getBoardDetail(
            @PathVariable Long boardId,
            ModelMap model
    ) throws ChangeSetPersister.NotFoundException {
        BoardDto boardDto = boardService.getBoardById(boardId);
        model.addAttribute("board", boardDto);
        return "boardDetail";
    }

/*    //게시글 작성 페이지 이동
    @GetMapping("/form")
    public String boardWrite(){

    }

    //게시글 업로드
    @PostMapping("/form")
    public String write(){

    }*/
}
