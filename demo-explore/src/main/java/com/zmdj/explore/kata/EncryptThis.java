package com.zmdj.explore.kata;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * You want to create secret messages which can be deciphered by the Decipher this! kata. Here are the conditions:

 * Your message is a string containing space separated words.
 * You need to encrypt each word in the message using the following rules:
 * The first letter needs to be converted to its ASCII code.
 * The second letter needs to be switched with the last letter
 * Keepin' it simple: There are no special characters in input.
 * @author zhangyunyun create on 2019/3/4
 */
public class EncryptThis {

    static Pattern pattern = Pattern.compile("( *)(\\S*)");

    public static String encryptThis(String text) {
        if (text == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder("");

        Matcher matcher = null;
        matcher = pattern.matcher(text);

        while (matcher.find()) {


            String word = matcher.group(2) != null ? matcher.group(2) : "";

            String space = matcher.group(1) != null ? matcher.group(1) : "";

            encryptOneWord(word, space, sb);

        }


        return sb.toString();

    }

    private static void encryptOneWord(String word, String space, StringBuilder sb) {
//        System.out.println(String.format("word=%s, space=%s.", word, space));

        char[] chars = word.toCharArray();


        if (chars.length > 0) {

            sb.append(space);

            char secondChar = 0;
            char lastChar = chars[chars.length - 1];
            for (int i = 0; i < chars.length; i++) {

                if (i == 0) {
                    sb.append(String.valueOf((byte)chars[0]));
                    continue;
                }

                if (i == 1) {
                    secondChar = chars[i];
                    sb.append(lastChar);
                    continue;
                }

                if (i == chars.length - 1 && chars.length > 2) {
                    sb.append(secondChar);
                    continue;
                }

                sb.append(chars[i]);
            }
        }

    }


}
