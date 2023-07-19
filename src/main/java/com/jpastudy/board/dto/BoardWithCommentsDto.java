package com.jpastudy.board.dto;

import com.jpastudy.board.domain.Board;
import com.jpastudy.board.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardWithCommentsDto {
    private Long id;
    private UserAccountDto userAccountDto;
    private Set<CommentDto> commentDtos;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public BoardWithCommentsDto(Long id, UserAccountDto userAccountDto, Set<CommentDto> commentDtos, String title, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.commentDtos = commentDtos;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }

    public static BoardWithCommentsDto of(Long id, UserAccountDto userAccountDto, Set<CommentDto> commentDtos, String title, String content, LocalDateTime createdAt, String createdBy ,LocalDateTime modifiedAt, String modifiedBy){
        return new BoardWithCommentsDto(id, userAccountDto, commentDtos, title, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static BoardWithCommentsDto from(Board entity){
        return new BoardWithCommentsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getComments().stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
