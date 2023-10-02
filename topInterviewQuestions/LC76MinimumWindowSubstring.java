
import java.util.HashMap;
import java.util.Map;

public class LC76MinimumWindowSubstring {
//    Given two strings s and t of lengths m and n respectively, return the minimum window substring
//    of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
//
//    The testcases will be generated such that the answer is unique.
//
//            Example 1:
//    Input: s = "ADOBECODEBANC", t = "ABC"
//    Output: "BANC"
//    Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
//
//            Example 2:
//    Input: s = "a", t = "a"
//    Output: "a"
//    Explanation: The entire string s is the minimum window.
//
//    Example 3:
//    Input: s = "a", t = "aa"
//    Output: ""
//    Explanation: Both 'a's from t must be included in the window.
//    Since the largest window of s only has one 'a', return empty string.
//
//
//    Constraints:
//
//    m == s.length
//    n == t.length
//    1 <= m, n <= 105
//    s and t consist of uppercase and lowercase English letters.
//
//    Follow up: Could you find an algorithm that runs in O(m + n) time?


    //sliding window, this is good
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char x : t.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int matched = 0;
        int start = 0;
        int minLen = s.length() + 1;
        int subStr = 0;
        for (int endWindow = 0; endWindow < s.length(); endWindow++) {
            char right = s.charAt(endWindow);
            if (map.containsKey(right)) {
                map.put(right, map.get(right) - 1);
                if (map.get(right) == 0) matched++;
            }

            while (matched == map.size()) {
                if (minLen > endWindow - start + 1) {
                    minLen = endWindow - start + 1;
                    subStr = start;
                }
                char deleted = s.charAt(start++);
                if (map.containsKey(deleted)) {
                    if (map.get(deleted) == 0) matched--;
                    map.put(deleted, map.get(deleted) + 1);
                }
            }
        }
        return minLen > s.length() ? "" : s.substring(subStr, subStr + minLen);
    }


    public String minWindow2(String s, String t) {
        // source = https://youtu.be/jSto0O4AJbM?si=zE3o4Rck2Rlkhh1Q
        if (t.isEmpty()) {
            return "";
        }

        Map<Character, Integer> countT = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (char c : t.toCharArray()) {
            countT.put(c, 1 + countT.getOrDefault(c, 0));
        }

        int have = 0;
        int need = countT.size();
        int[] res = {-1, -1};
        int resLen = Integer.MAX_VALUE;
        int l = 0;

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            window.put(c, 1 + window.getOrDefault(c, 0));

            if (countT.containsKey(c) && window.get(c).equals(countT.get(c))) {
                have++;
            }

            while (have == need) {
                if (r - l + 1 < resLen) {
                    res[0] = l;
                    res[1] = r;
                    resLen = r - l + 1;
                }

                window.put(s.charAt(l), window.get(s.charAt(l)) - 1);

                if (countT.containsKey(s.charAt(l)) && window.get(s.charAt(l)) < countT.get(s.charAt(l))) {
                    have--;
                }

                l++;
            }
        }

        int left = res[0];
        int right = res[1];

        return resLen != Integer.MAX_VALUE ? s.substring(left, right + 1) : "";
    }
}
