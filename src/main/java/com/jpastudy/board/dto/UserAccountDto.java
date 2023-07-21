package com.jpastudy.board.dto;

import com.jpastudy.board.domain.UserAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserAccountDto {

    private String userId;
    private String pw;
    private String email;
    private String name;

 /*   private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;*/

    public static UserAccountDto of(String userId, String pw, String email, String name){
        return new UserAccountDto(userId, pw, email, name /*null,null,null,null*/);
    }

    public UserAccountDto(String userId, String pw, String email, String name/*LocalDateTime createdAt, String createdBy,LocalDateTime modifiedAt, String modifiedBy*/){
        this.userId = userId;
        this.pw = pw;
        this.email = email;
        this.name = name;
/*        this.createdAt = createdAt;
        this.createdBy=createdBy;
        this.modifiedAt = modifiedAt;
        this.modifiedBy =modifiedBy;*/
    }

    public static UserAccountDto from(UserAccount entity){
        return new UserAccountDto(
                entity.getUserId(),
                entity.getPw(),
                entity.getEmail(),
                entity.getName()
/*                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()*/
        );
    }


    public UserAccount toEntity(){
        return UserAccount.of(
                userId,
                pw,
                name,
                email
        );
    }

}
