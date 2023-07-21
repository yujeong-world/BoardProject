package com.jpastudy.board.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Entity
public class UserAccount extends AuditingFields{
    @Id
    @Column(length = 15)
    private String userId;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;



    protected UserAccount() {}

    @Builder
    private UserAccount(String userId, String pw, String name, String email){
        this.userId = userId;
        this.pw=pw;
        this.name=name;
        this.email=email;
    }

    public static UserAccount of(String userId, String pw, String name, String email){
        return new UserAccount(userId,pw,name,email);
    }

}
