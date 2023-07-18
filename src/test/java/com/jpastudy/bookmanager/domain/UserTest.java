package com.jpastudy.bookmanager.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test(){
        User user = new User();
        user.setEmail("yujeong@abc.com");
        user.setName("yujeong");

        System.out.println(">>>"+user.toString());
    }

}