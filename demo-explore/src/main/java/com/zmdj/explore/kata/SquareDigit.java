package com.zmdj.explore.kata;

/**
 * Welcome. In this kata, you are asked to square every digit of a number.

 * For example, if we run 9119 through the function, 811181 will come out, because 92 is 81 and 12 is 1.
 * @author zhangyunyun create on 2019/3/4
 */
public class SquareDigit {

    public int squareDigits(int n) {

        int i = 0;
        int result = 0;
        do {
            int mod = n % 10;

            int modSqrt = mod * mod;

            result += modSqrt * Math.pow(10, i);

            if (modSqrt > 9) {
                i = i + 2;
            } else {
                i ++;
            }

            n = n / 10;

        } while (n >= 1);



        return result;
    }


}
