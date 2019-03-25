package com.zmdj.explore.kata;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array, find the int that appears an odd number of times.

 There will always be only one integer that appears an odd number of times.
 * @author zhangyunyun create on 2019/3/6
 */
public class FindOdd {

    public static int findIt(int[] a) {

//        Set<Integer> sets = new HashSet<>();
//
//        for (int num : a) {
//
//            if (sets.contains(num)) {
//                sets.remove(num);
//            } else {
//                sets.add(num);
//            }
//        }
//
//        return sets.stream().findFirst().get();

        int xor = 0;
        for (int i = 0; i < a.length; i++) {
            xor ^= a[i];
        }
        return xor;

    }
}
