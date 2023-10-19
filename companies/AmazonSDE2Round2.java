
public class AmazonSDE2Round2 {
//    Check whether given input string s matches with regular expression r with support of:
//
//            '.'   matches any single character.
//            '*'   matches zero or more of the preceding element.
//
//    s = ab, r =ab, output=true
//    s = ab, r =a., output=true
//    s = ab, r =abc, output=true
//    s = ab, r =ac*b, output=true
//
//
//
//    s = ac, r =ab*b, output=true
//
//            0,0
//            1,1    => 1, 3 => true ||
//            => 2,1  => false
//            true
//
//
//            0,0
//            1,1  => 1,3 =>false ||
//            => 2,1 => false
//            false
//
//    s = ab, r =ab**b,
//
//
    public static boolean check(String s, String r) {
        return recurseCheck(0,0,s,r);
    }
//
    public static boolean recurseCheck(int i, int j, String s, String r) {
        if (i == s.length() && j == r.length()) {
            return true;
        }
        if (j >= r.length()) {
            return false;
        }
        boolean match = i < s.length() && (s.charAt(i) == r.charAt(j) || r.charAt(j) == '.');
        if (j+1 < r.length() && r.charAt(j) == '*') {
            return recurseCheck(i, j+2, s,r) ||
                    (match && recurseCheck(i+1, j, s,r));
        }

        if (match) {
            return recurseCheck(i+1, j+1, s, r);
        }
        return false;
    }

    // 2nd question, if add the () in the pattern before star
//* () -> group characters and we can apply * on this group.
//
//            a(bc)* -> abcbcbcbc
//
//    public boolean check(String s, String r) {
//        recurseCheck(0,0,s,r, 1);
//    }
//
//    public boolean recurseCheck(int i, int j, String s, String r, int compare) {
//        if (i == s.length() && j == r.length()) {
//            return true;
//        }
//        if (j >= r.length()) {
//            return false;
//        }
//        boolean match = i < s.length() && compare == 1 && (s.charAt(i) == r.charAt(j) || r.charAt(j) == '.');
//
//
//        if (r.charAt(j) == '(') {
//            int currIndex = j+1;
//            while(r.charAt(j) != ')') {
//                currIndex++;
//                compare++;
//            }
//        }
//        if (j+1 < r.length() && r.charAt(j) == '*') {
//            return recurseCheck(i, j+2, s,r) ||
//                    (match && recurseCheck(i+1, j, s,r));
//        }
//
//        if (match) {
//            return recurseCheck(i+1, j+1, s, r);
//        }
//        return false;
//    }
//
//
//    abcbcbcbc   =  a(bc)*

}
