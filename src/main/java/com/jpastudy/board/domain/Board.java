package com.jpastudy.board.domain;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@ToString
@Getter
@Entity
public class Board extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private UserAccount userAccount; // 유저 아이디

    @ToString.Exclude
    @OrderBy("createdAt DESC")
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private final Set<Comment> comments = new LinkedHashSet<>();


    @Setter
    @Column(length = 500, nullable = false)
    private String title;

    @Setter
    @Column(length = 500, nullable = false)
    private String content;

    protected Board() {} //기본 생성자는 외부에서 접근 불가하도록

    @Builder
    public Board(UserAccount userAccount, String title, String content){
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
    }

    public static Board of(UserAccount userAccount, String title, String content){
        return new Board(userAccount,title,content);
    }

}
