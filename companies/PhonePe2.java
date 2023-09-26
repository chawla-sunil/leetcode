
import java.util.*;

// Main class should be named 'Solution' and should not be public.
class PhonePe2 {
    public static void main(String[] args) {
        System.out.println("Hello, World");
        // for question1
        List<List<Integer>> a = new ArrayList<>();
        a.add(Arrays.asList(2));
        a.add((Arrays.asList(3,4)));
        a.add((Arrays.asList(6,5,7)));
        a.add((Arrays.asList(4,1,8,3)));
        // for question 1
         int ans = min(a);
         System.out.println(ans);

        System.out.println("======= Solution 2 =======");
         // for question 2
        binary(10);

        System.out.println("======= Solution 2 with O(1) Space =======");
        binarySolution2(10);
    }

    // For each step, you may move to an adjacent number of the row below. More formally,
    // if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
// [[2],[3,4],[6,5,7],[4,1,8,3]]
// 2 + 3 + 5 + 1 = 11
// [[2],[7,4],[6,11,4],[4,1,8,3]]
    public static int min(List<List<Integer>> list) {
         for (int i = list.size() -2; i >=0; i--) {
             for (int j = list.get(i).size() - 1; j >=0; j--) {
                 int min1 = Math.min(list.get(i+1).get(j), list.get(i+1).get(j+1));
                 list.get(i).set(j, list.get(i).get(j) + min1);
             }
         }
         return list.get(0).get(0);
     }



// print 1 to n in binary format without any mathematical/binary operator
// 10

// 1
// 10
// 11
// 100
// 101
// 110
// 111
// 1000
// 1001
// 1010
    public static void binary(int n) {
        Queue<String> q = new LinkedList<>();
        q.add("1");
        while(n > 0) {
            String s = q.poll();
            System.out.println(s);

            q.add(s + "0");
            q.add(s + "1");
            n--;
        }
    }

    public static void binarySolution2(int n) {
        String binary = "1";
        System.out.println(binary);

        for (int i = 2; i <= n; i++) {
            String carry = "1";
            StringBuilder binaryBuilder = new StringBuilder(binary);

            int j = binary.length() - 1;
            while (j >= 0 && carry.equals("1")) {
                char currentBit = binary.charAt(j);
                if (currentBit == '1') {
                    binaryBuilder.setCharAt(j, '0');
                } else {
                    binaryBuilder.setCharAt(j, '1');
                    carry = "0";
                }
                j--;
            }
            if (carry.equals("1")) {
                binaryBuilder.insert(0, '1');
            }
            binary = binaryBuilder.toString();
            System.out.println(binary);
        }
    }

}
