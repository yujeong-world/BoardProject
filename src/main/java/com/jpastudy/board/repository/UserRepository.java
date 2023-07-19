package com.jpastudy.board.repository;

import com.jpastudy.board.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, String> {
}
