package com.jpastudy.board.dto;

import com.jpastudy.board.domain.Board;
import com.jpastudy.board.domain.UserAccount;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private UserAccountDto userAccountDto;
    private String title;
    private String content;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public static BoardDto of(UserAccountDto userAccountDto, String title, String content){
        return new BoardDto(null,userAccountDto,title,content,null,null,null,null);
    }
    @Builder
    public BoardDto(Long id, UserAccountDto userAccountDto, String title, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy){
        this.id = id;
        this.userAccountDto = userAccountDto;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy = modifiedBy;
    }
    public static BoardDto from(Board entity){
        return new BoardDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Board toEntity(UserAccount userAccount){
        return Board.of(
                userAccount,
                title,
                content
        );
    }
}
