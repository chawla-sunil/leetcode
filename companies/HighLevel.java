
public class HighLevel {
    // First 30 min about work then 2 questions
    // Question 1 is to matrix spiral traversal
//    A
//
//    4 1 6 8
//            3 9 2 7
//            8 2 5 6
//            7 3 9 1
//
//
//            4 1 6 8 7 6 1 9 3 7 8 3 9 2 5 2
//
//
//            4 - 1 - 6 - 8
//            |
//            3 - 9 - 2   7
//            |       |   |
//            8   2 - 5   6
//            |           |
//            7 - 3 - 9 - 1
//
//
//    A = [[4,1,6,8],[3,9,2,7],[8,2,5,6],[7,3,9,1]];
//
//    WAF to print an M*M matrix into a string, into a spiral pattern starting from A[0][0].
//    print(A,m)
//
//
//    int m =  A.lenght; row
//    int n =  A[0].length; col
//
//    int right = n - 1;
//    int left = 0;
//    int bottom = m-1;
//    int top = 0;
//
//    int dir = 0;
//    while(top <= bottom && left <= right) {
//        if (dir == 0) {
//            for (int i = left; i <= right; i++) {
//                System.out.println(A[top][i]);
//            }
//            top++;
//        } else if (dir == 1) {
//            for (int i = top; i <= bottom; i++) {
//                System.out.println(A[i][right]);
//            }
//            right--;
//        } else if (dir == 2) {
//            for (int i = right; i >= left; i--) {
//                System.out.println(A[bottom][i]);
//            }
//            bottom--;
//        } else if (dir == 3) {
//            for (int i = bottom; i >= top; i--) {
//                System.out.println(A[i][left]);
//            }
//            left++;
//        }
//
//
//        dir = (dir + 1) % 4;
//    }
//
//

    // 2nd question was to find angle between hour and min minute needle.
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        double a = findAngle(10, 15);
        System.out.println(a);
    }

    public static double findAngle(int h, int m) {
        if (h < 0 || m < 0 || h > 12 || m > 60) {
            return 0;
        }

        double hA = 0.5 * (h*60 + m); // 315
        double mA = 6 * m; // 180
        System.out.println(hA + "-" + mA);

        double res = Math.abs(hA - mA);
        res = Math.min(res, 360 -  res);

        return res;
    }



}
