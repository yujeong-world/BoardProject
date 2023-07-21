package com.jpastudy.board.repository;

import com.jpastudy.board.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    Optional<UserAccount> findByName(String name);
    List<UserAccount> findAll();

    Optional<UserAccount> findByUserId(String username);
}
