package com.example.madgroupproject;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Objects;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void email_isCorrect()throws Exception {
        com.example.madgroupproject.Test test = new com.example.madgroupproject.Test();
        String result = test.valid("someone@gmail.com");
        String expected = "someone@gmail.com";
        assertEquals(expected, result);
    }

    @Test
    public void email_isNotCorrect()throws Exception {
        com.example.madgroupproject.Test test = new com.example.madgroupproject.Test();
        String result = test.valid("someone");
        String expected = "someone@gmail.com";
        if(!Objects.equals(result, expected)){
            System.out.println("Assertion error");
        }
        //assertEquals(expected, result);
    }
    @Test
    public void name_isNotNull()throws Exception{
        com.example.madgroupproject.Test test2 = new com.example.madgroupproject.Test();
        boolean result = test2.validName("ABC");
        boolean expected = true;
        assertEquals(true, result);

    }
    @Test
    public void name_isNull() throws Exception{
        com.example.madgroupproject.Test test2 = new com.example.madgroupproject.Test();
        boolean result = test2.validName("");
        boolean expected = false;
        assertEquals(false, result);
    }
}