package com.jpastudy.board.dto;

import com.jpastudy.board.domain.Board;
import com.jpastudy.board.domain.Comment;
import com.jpastudy.board.domain.UserAccount;

import java.time.LocalDateTime;

public class CommentDto {
    private Long id;
    private Long boardId;
    private UserAccountDto userAccountDto;

    private String content;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public CommentDto(Long id, Long boardId, UserAccountDto userAccountDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.boardId = boardId;
        this.userAccountDto = userAccountDto;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static CommentDto of(Long boardId, UserAccountDto userAccountDto,String content){
        return new CommentDto(null, boardId, userAccountDto, content, null, null, null,null);
    }

    public static CommentDto from(Comment entity){
        return new CommentDto(
                entity.getId(),
                entity.getBoard().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Comment toEntity(Board board, UserAccount userAccount){
        return Comment.of(board, userAccount, content);
    }
}
