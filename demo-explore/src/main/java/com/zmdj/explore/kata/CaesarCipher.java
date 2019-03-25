package com.zmdj.explore.kata;

import java.util.ArrayList;
import java.util.List;

/**
 * The action of a Caesar cipher is to replace each plaintext letter with a different one a fixed number of places up or down the alphabet.

 This program performs a variation of the Caesar shift. The shift increases by 1 for each character (on each iteration).

 If the shift is initially 1, the first character of the message to be encoded will be shifted by 1, the second character will be shifted by 2, etc...

 Coding: Parameters and return of function "movingShift"
 param s: a string to be coded

 param shift: an integer giving the initial shift

 The function "movingShift" first codes the entire string and then returns an array of strings containing the coded string in 5 parts (five parts because, to avoid more risks, the coded message will be given to five runners, one piece for each runner).

 If possible the message will be equally divided by message length between the five runners. If this is not possible, parts 1 to 5 will have subsequently non-increasing lengths, such that parts 1 to 4 are at least as long as when evenly divided, but at most 1 longer. If the last part is the empty string this empty string must be shown in the resulting array.

 For example, if the coded message has a length of 17 the five parts will have lengths of 4, 4, 4, 4, 1. The parts 1, 2, 3, 4 are evenly split and the last part of length 1 is shorter. If the length is 16 the parts will be of lengths 4, 4, 4, 4, 0. Parts 1, 2, 3, 4 are evenly split and the fifth runner will stay at home since his part is the empty string. If the length is 11, equal parts would be of length 2.2, hence parts will be of lengths 3, 3, 3, 2, 0.

 You will also implement a "demovingShift" function with two parameters

 Decoding: parameters and return of function "demovingShift"
 1) an array of strings: s (possibly resulting from "movingShift", with 5 strings)

 2) an int shift

 "demovingShift" returns a string.

 Example:
 u = "I should have known that you would have a perfect answer for me!!!"

 movingShift(u, 1) returns :

 v = ["J vltasl rlhr ", "zdfog odxr ypw", " atasl rlhr p ", "gwkzzyq zntyhv", " lvz wp!!!"]

 (quotes added in order to see the strings and the spaces, your program won't write these quotes, see Example Test Cases)

 and demovingShift(v, 1) returns u.

 #Ref:

 Caesar Cipher : http://en.wikipedia.org/wiki/Caesar_cipher
 * @author zhangyunyun create on 2019/3/8
 */
public class CaesarCipher {

    private static char charShift(char ch, int shift) {
        if (!Character.isLetter(ch)) {
            return ch;
        }
        byte firstCharByte = (byte)(Character.isLowerCase(ch) ? 'a': 'A');

        int offset = (((byte)ch - firstCharByte) + shift) % 26;

        if (offset < 0) {
            offset += 26;
        }

        return (char)(offset + firstCharByte);
    }

    public static List<String> movingShift(String s, int shift) {

        int segmentCount = s.length() % 5 > 0 ? (s.length()/5 + 1) : s.length()/5;

        List<String> list = new ArrayList<>();
        char[] chars = s.toCharArray();

        StringBuilder sb = null;
        for (int i=0; i<chars.length; i++) {

            if (i % segmentCount == 0) {
                if (sb != null && sb.length() > 0) {
                    list.add(sb.toString());
                }

                sb = new StringBuilder("");
            }

            sb.append(charShift(chars[i], shift++));

        }

        if (sb != null && sb.length() > 0) {
            list.add(sb.toString());
        }

        return list;
    }

    public static String  demovingShift(List<String> s, int shift) {

        StringBuilder sb = new StringBuilder("");
        for (String str : s) {
            sb.append(str);
        }

        char[] chars = sb.toString().toCharArray();

        StringBuilder newSb = new StringBuilder("");
        for (int i=0; i<chars.length; i++) {

            char ch = chars[i];
            newSb.append(charShift(ch, -1 * shift++));

        }


        return newSb.toString();
    }

    public static void main(String[] args) {
        int count = charShift('u', -5);
        System.out.println((char)(count));
    }
}
