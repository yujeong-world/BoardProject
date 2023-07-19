package com.jpastudy.board.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Entity
public class Comment extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false) //optional로 필수값 지정
    private Board board; //게시글 id

    @Setter
    @JoinColumn(name = "userId")
    @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Setter
    @Column(nullable = false)
    private String content;

    protected Comment() {}

    @Builder
    private Comment(Board board, UserAccount userAccount, String content){
        this.board=board;
        this.userAccount=userAccount;
        this.content=content;
    }

    public static Comment of(Board board, UserAccount userAccount, String content){
        return new Comment(board,userAccount,content);
    }

}
