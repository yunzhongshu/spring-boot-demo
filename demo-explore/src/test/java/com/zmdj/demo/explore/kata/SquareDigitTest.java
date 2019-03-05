package com.zmdj.demo.explore.kata;

import com.zmdj.explore.kata.SquareDigit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author zhangyunyun create on 2019/3/4
 */
public class SquareDigitTest {

    @Test
    public void test() {

//        Assert.assertEquals(81, new SquareDigit().squareDigits(9));

        Assert.assertEquals(811181, new SquareDigit().squareDigits(9119));
    }

}
