package com.example.madgroupproject;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    User user = new User();

    @Test
    public void getUsername() {

        user.setUsername("pasan");

        assertEquals("pasan",user.getUsername());

    }

    @Test
    public void getEmail() {

        user.setEmail("pasan@gmail.com");
        assertEquals("pasan@gmail.com",user.getEmail());

    }

    @Test
    public void getPhoneNo(){

        user.setPhoneNo("0774512369");
        assertEquals("0774512369",user.getPhoneNo());

    }
}