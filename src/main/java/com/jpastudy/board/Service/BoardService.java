package com.jpastudy.board.Service;

import com.jpastudy.board.domain.Board;
import com.jpastudy.board.dto.BoardDto;
import com.jpastudy.board.dto.UserAccountDto;
import com.jpastudy.board.repository.BoardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardsRepository boardsRepository;


    //게시글 목록 가져오기
    public Page<BoardDto> getAllBoard(Pageable pageable){
        Page<Board> boards = boardsRepository.findAllByOrderByCreatedAtDesc(pageable);
       /* return boards.stream().map(BoardDto::from).collect(Collectors.toList());*/
        return boards.map(BoardDto::from);
    }

    //페이징 처리된 게시글 목록 가지고 오기
/*    public Page<BoardDto> getAllBoardSortedByCreatedAtDesc(Pageable pageable){
        Page<Board> boardPage = boardsRepository.findAllByOrderByCreatedAtDesc(pageable);
        return boardPage.map(BoardDto::from);
    }*/

    //게시글 상세 정보 가지고 오기
    public BoardDto getBoardById(Long boardId) throws ChangeSetPersister.NotFoundException {
        Board board = boardsRepository.findById(boardId).orElseThrow(()->new ChangeSetPersister.NotFoundException());
        return BoardDto.from(board);
    }
}
