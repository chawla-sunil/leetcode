package org.example.companies;

import java.util.Arrays;

public class IG {
    public static String solve(String inputStr) {
        // It was personal test only not asked in interview
        // given a string sort it such a way capital letter stay at there original position and sort small letters
        // example, input => "Google Mail"    output => "Gaegil Mloo"
        StringBuilder lcLetters = new StringBuilder();

        for (char ch : inputStr.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                lcLetters.append(ch);
            }
        }

        char[] sortedLc = lcLetters.toString().toCharArray();
        Arrays.sort(sortedLc);

        StringBuilder res = new StringBuilder();
        int lowercaseIndex = 0;

        for (char ch : inputStr.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                res.append(sortedLc[lowercaseIndex]);
                lowercaseIndex++;
            } else {
                res.append(ch);
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(solve("Google Mail"));
    }
}
