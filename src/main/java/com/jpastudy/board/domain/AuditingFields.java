package com.jpastudy.board.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = true, updatable = false) //updatable = false 이필드는 업데이트 불가하다, 최초한번만 생성가능
    private LocalDateTime createdAt; //생성일시
    @CreatedBy
    @Column(nullable = true, length = 100, updatable = false)
    private String createdBy; //생성자
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = true)
    private LocalDateTime modifiedAt; //수정일시
    @LastModifiedBy
    @Column(nullable = true, length = 100)
    private String modifiedBy; // 수정자
}
