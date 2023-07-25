package com.jpastudy.board.controller;

import com.jpastudy.board.Service.BoardService;
import com.jpastudy.board.Service.CommentService;
import com.jpastudy.board.dto.BoardDto;
import com.jpastudy.board.dto.BoardWithCommentsDto;
import com.jpastudy.board.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;


    //전체 게시물
    //페이징 page: 디폴트 페이지, size : 한 페이지 게시글 수, sort : 정렬기준칼럼,direction: 정렬 순서
    @GetMapping
    public String getAllBoards(
            ModelMap modelMap,
            @PageableDefault(page = 0, size = 5, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable,
            String searchKeyword
    ){
        //변수의 유효 범위 문제로 인해, 블록 밖에 변수를 선언해줌
        Page<BoardDto> boards;
        //검색 했을 때와 검색하지 않았을 때를 구분
        if(searchKeyword == null){
            boards = boardService.getAllBoard(pageable); //기존의 리스트
        }else{
            boards = boardService.getBoardSearch(searchKeyword, pageable);
        }

        //현재 사용자 정보 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = authentication.isAuthenticated();

        //현재 사용자가 인증되어 있으면 ROLE_USER 권한이 있는지 확인
        boolean hasUserRole = authentication.getAuthorities().stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"));

        modelMap.addAttribute("boards", boards);
        modelMap.addAttribute("hasUserRole", hasUserRole);
        modelMap.addAttribute("isLoggedIn", isLoggedIn);
        return "board";
    }

    //게시물 상세 페이지(게시글 단건 조회)
    @GetMapping("/{boardId}")
    public String getBoardDetail(
            @PathVariable Long boardId,
            ModelMap model
    ) throws ChangeSetPersister.NotFoundException {
        //현재 사용자 정보 확인
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = authentication.isAuthenticated();
        String userName= authentication.getName();

        //현재 사용자가 인증되어 있으면 ROLE_USER 권한이 있는지 확인
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"));

        model.addAttribute("hasUserRole", hasUserRole);
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("userName", userName);

        BoardWithCommentsDto boardWithCommentsDto = boardService.getBoarWithCommentdById(boardId);
        model.addAttribute("board", boardWithCommentsDto);
        return "boardDetail";
    }

    //댓글 작성
    @PostMapping("/{boardId}")
    public String writeComment(
            @PathVariable Long boardId,
            @ModelAttribute CommentDto commentDto
    ) throws ChangeSetPersister.NotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userId = userDetails.getUsername();


        commentService.saveBoardComment(commentDto, userId, boardId);
        return "redirect:/board/"+boardId;
    }

    //댓글 삭제
    @PostMapping("/{boardId}/comment/{commentId}")
    public String boardCommentDelete(
            @PathVariable Long boardId,
            @PathVariable Long commentId
    ){

        commentService.CommentDelete(commentId);
        return "redirect:/board/"+boardId;
    }


    //게시판 글 삭제
    @PostMapping("/{boardId}/deleteBoard")
    public String boardDelete(
            @PathVariable Long boardId
    ){
        boardService.boardDelete(boardId);
        return "redirect:/board";
    }

    //게시판 글 수정
    @PostMapping("/{boardId}/modifyBoard")
    public String boardModify(
            @PathVariable Long boardId,
            @RequestParam String title,
            @RequestParam String content
    ) throws ChangeSetPersister.NotFoundException {
        BoardDto boardDto = boardService.boardModify(boardId,title,content);
        return "redirect:/board/"+ boardId;
    }

    //게시판 수정 페이지로 이동
    @GetMapping("/{boardId}/modifyBoard/write")
    public String boardModifyWrite(
            @PathVariable Long boardId,
            ModelMap model
    ) throws ChangeSetPersister.NotFoundException {
        BoardDto boardDto = boardService.getBoardModifyById(boardId);
        model.addAttribute("board", boardDto);
        return "writeModify";
    }


    //글쓰기
    @GetMapping("/write")
    public String writeBoard(){
        return "write";
    }

    @PostMapping("/write")
    public String postWrite(
            @ModelAttribute BoardDto boardDto
    ){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userId = userDetails.getUsername();
        boardService.addBoard(boardDto,userId);

        return "redirect:/board";
    }




}
