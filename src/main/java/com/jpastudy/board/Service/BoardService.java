package com.jpastudy.board.Service;

import com.jpastudy.board.domain.Board;
import com.jpastudy.board.domain.Comment;
import com.jpastudy.board.domain.UserAccount;
import com.jpastudy.board.dto.BoardDto;
import com.jpastudy.board.dto.BoardWithCommentsDto;
import com.jpastudy.board.dto.UserAccountDto;
import com.jpastudy.board.repository.BoardsRepository;
import com.jpastudy.board.repository.CommentRepository;
import com.jpastudy.board.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardsRepository boardsRepository;
    private final UserAccountRepository userAccountRepository;



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
    public BoardWithCommentsDto getBoardById(Long boardId) throws ChangeSetPersister.NotFoundException {
        Board board = boardsRepository.findById(boardId).orElseThrow(()->new ChangeSetPersister.NotFoundException());
        return BoardWithCommentsDto.from(board);
    }

    //게시글 검색 기능
    @Transactional
    public Page<BoardDto> getBoardSearch(String keyword, Pageable pageable){
        Page<Board> boardslist = boardsRepository.findByTitleContaining(keyword, pageable);
        return boardslist.map(BoardDto::from);
    }

    //글쓰기
    @Transactional
    public BoardDto addBoard(BoardDto boardDto, String userId){
        UserAccount userAccount = userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        boardDto.setContent(boardDto.getContent());
        boardDto.setTitle(boardDto.getTitle());
        //Board board = BoardDto.of(UserAccountDto.from(userAccount), boardDto.getTitle(), boardDto.getContent());
        Board board = boardsRepository.save(boardDto.toEntity(userAccount));

        return BoardDto.from(board);
    }

    @Transactional
    public void boardDelete(Long boardId){
        boardsRepository.deleteById(boardId);
    }



}
