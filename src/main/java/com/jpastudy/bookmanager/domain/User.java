package com.jpastudy.bookmanager.domain;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor //인자가 없는 생성자, 항상 필요하므로 생성 해야 함
@AllArgsConstructor // 모든 필드를 받아서 인자를 생성자를 만듦
//@RequiredArgsConstructor //필요한 인자만을 받아서 생성자 만듦 @NonNull으로 필수 값을 지정 할 수 있음
@Data //getter, setter, RequiredArgsConstructor, toString, EqualsAndHashCode 모두 포함
@Builder
public class User {
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
