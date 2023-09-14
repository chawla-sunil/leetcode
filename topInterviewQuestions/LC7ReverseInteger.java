
public class LC7ReverseInteger {
//    Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
//
//    Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
//
//
//
//    Example 1:
//
//    Input: x = 123
//    Output: 321
//    Example 2:
//
//    Input: x = -123
//    Output: -321
//    Example 3:
//
//    Input: x = 120
//    Output: 21
//
//
//    Constraints:
//
//            -2^31 <= x <= (2^31) - 1        // 1 bit is for sign(+Ve or -Ve)

    public int reverse(int x) {
        StringBuilder s = new StringBuilder();
        s.append(Math.abs(x));
        s.reverse();
        if(s.length() >= 10) {
            int cut1 = Integer.parseInt(s.substring(0, 5));
            int cut2 = Integer.parseInt(s.substring(5, 10));
            // since input is int so it will never be grater then 10 digits
            String max = Integer.toString(Integer.MAX_VALUE);
            int a = Integer.parseInt(max.substring(0, 5));
            int b = Integer.parseInt(max.substring(5, 10));
            System.out.println(" " + a + " " + b + " " + max);
            if (cut1 > a || cut2 > b) {
                return 0;
            }
        }
        int num = Integer.parseInt(s.toString());
        return x > 0 ? num : -num;
    }
}
