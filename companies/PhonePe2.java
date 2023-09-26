
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
         int ans = min(a);
         System.out.println(ans);

         // for question 2
        binary(10);
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
}
