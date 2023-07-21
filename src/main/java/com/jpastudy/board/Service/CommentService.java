package com.jpastudy.board.Service;

import com.jpastudy.board.domain.Board;
import com.jpastudy.board.domain.Comment;
import com.jpastudy.board.domain.UserAccount;
import com.jpastudy.board.dto.CommentDto;
import com.jpastudy.board.dto.UserAccountDto;
import com.jpastudy.board.repository.BoardsRepository;
import com.jpastudy.board.repository.CommentRepository;
import com.jpastudy.board.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardsRepository boardsRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional
    public CommentDto saveBoardComment(CommentDto commentDto, String userId, Long boardId){
/*
        try{
            Board board = boardsRepository.findById(boardId)
                    .orElseThrow(()->new EntityNotFoundException("boardId not found"));
            UserAccount userAccount = userAccountRepository.findByUserId(userId)
                    .orElseThrow(() -> new NoSuchElementException("User not found"));
            commentDto.setContent(commentDto.getContent());

            commentRepository.save(commentDto.toEntity(board, userAccount));
        }catch (EntityNotFoundException e){
            log.warn("댓글 저장실패. 댓글 작성에 필요한 정보를 찾을 수 없습니다.");
        }
*/
        UserAccount userAccount = userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Board board = boardsRepository.findById(boardId)
                .orElseThrow(()->new EntityNotFoundException("boardId not found"));
        commentDto.setContent(commentDto.getContent());
        //commentDto.setBoardId(board.getId());
        //commentDto.setUserAccountDto(UserAccountDto.from(userAccount));
        //Comment comment = commentRepository.save(commentDto.toEntity(board,userAccount));

        Comment comment = commentRepository.save(CommentDto.of(boardId, UserAccountDto.from(userAccount), commentDto.getContent()));
        return CommentDto.from(comment);

    }

}
