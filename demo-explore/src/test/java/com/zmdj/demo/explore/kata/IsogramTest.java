package com.zmdj.demo.explore.kata;

import com.zmdj.explore.kata.Isogram;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * @author zhangyunyun create on 2019/3/6
 */
public class IsogramTest {

    @Test
    public void FixedTests() {
        assertEquals(true, Isogram.isIsogram("Dermatoglyphics"));
        assertEquals(true, Isogram.isIsogram("isogram"));
        assertEquals(false, Isogram.isIsogram("moose"));
        assertEquals(false, Isogram.isIsogram("isIsogram"));
        assertEquals(false, Isogram.isIsogram("aba"));
        assertEquals(false, Isogram.isIsogram("moOse"));
        assertEquals(true, Isogram.isIsogram("thumbscrewjapingly"));
        assertEquals(true, Isogram.isIsogram(""));
    }
}
