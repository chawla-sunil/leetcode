
public class LC5LongestPalindromicSubstring {
//    Given a string s, return the longest palindromic substring in s.
//
//    Example 1:
//
//    Input: s = "babad"
//    Output: "bab"
//    Explanation: "aba" is also a valid answer.
//            Example 2:
//
//    Input: s = "cbbd"
//    Output: "bb"
//
//
//    Constraints:
//
//            1 <= s.length <= 1000
//    s consist of only digits and English letters.
//
    int maxLength = 0;
    int startOfPalindrome = 0;
    public String longestPalindrome(String s) {
        char[] input = s.toCharArray();

        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            expendPalindrome(input, i, i);
            expendPalindrome(input, i , i+1);
        }
        return s.substring(startOfPalindrome, startOfPalindrome + maxLength);
    }

    public void expendPalindrome(char[] input, int j, int k) {
        while(j >= 0 && k < input.length && input[j] == input[k]) {
            j--;
            k++;
        }
        if (maxLength < k - j - 1) {
            maxLength = k - j - 1;
            startOfPalindrome = j + 1;
        }
    }
}
