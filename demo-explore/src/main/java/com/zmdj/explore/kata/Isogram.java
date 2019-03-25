package com.zmdj.explore.kata;

import java.util.HashSet;
import java.util.Set;

/**
 * An isogram is a word that has no repeating letters, consecutive or non-consecutive. Implement a function that determines whether a string that contains only letters is an isogram. Assume the empty string is an isogram. Ignore letter case.

 isIsogram "Dermatoglyphics" == true
 isIsogram "moose" == false
 isIsogram "aba" == false
 * @author zhangyunyun create on 2019/3/6
 */
public class Isogram {
    public static boolean  isIsogram(String str) {

//        char[] chars = str.toCharArray();
//
//        Set<Character> charSets = new HashSet<>();
//
//        for (char ch : chars) {
//
//           char lowChar = Character.toLowerCase(ch);
//           if (charSets.contains(lowChar)) {
//               return false;
//           }
//           charSets.add(lowChar);
//        }
//        return true;

        return str.toLowerCase().chars().distinct().count() == str.length();

    }
}
