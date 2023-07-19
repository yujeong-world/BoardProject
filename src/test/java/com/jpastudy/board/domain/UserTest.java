package com.jpastudy.board.domain;

import org.junit.jupiter.api.Test;

class UserTest {
    @Test
    void test(){
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail("yujeong@abc.com");
        userAccount.setName("yujeong");

        System.out.println(">>>"+ userAccount.toString());
    }

}