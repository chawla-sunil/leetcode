package org.example.companies;

public class AmazonSDE2Round1 {
//     Question 1 => Rotated and Sorted Array, find element
//    function foo(items) {
//        var i;
//        for (i = 0; i < items.length; i++) {
//            alert("Welcome To LiveCode " + items[i]);
//        }
//    }
//
//    Question 1 : Find a element in given array which is sorted and rotated.
//            arr = {4,5,6,7,8,9,10,1,2,3}
//
//
//    Input => 5, Output => Present
//    Input => 10 Output => Present
//    Input => 12 Output => Not_Present
//
//
//            Answer => dry run with examples
//            arr = {1,2,3,4,5,6,7,8,9,10}
//    N = 2
//    N = 8
//
//    low = 0, high = 9,
//    mid = 4,
//
//    high = 3
//    mid = 1;
//    low, mid, high
//0   4  , 9
//        5,  7  , 9
//    {4,5,6,7,8,9,10,1,2,3}
//    low, mid, high
//            N = 2
//0, 4, 9
//        5, 7, 9
//        8, 8,, 9
//
//    N = 8
//            0, 4, 9
//
//    My code =>
//            while(low <= high) {
//        int mid = (low + high) / 2;
//        if (arr[mid] == given) {return mid;}
//
//        if (arr[low] <= arr[mid]) {
//            if (arr[low]<= given && given < arr[mid]) {
//                high = mid - 1;
//            } else {
//                low  = mid + 1;
//            }
//        } else {
//            if (arr[mid] < given && given <= arr[high]) {
//                low = mid + 1;
//            } else {
//                high = mid - 1;
//            }
//        }
//    }
//
//
//    Question 2 : Spiral Order tree traversal
//          1
//                  2      3
//                  4   5  6   7
//    Output :
//            1324567
//            (or)
//            1237654,.
//
//
//    My Answer =>
//
//    Deque<Node> dp = new ArrayDeque<>();
//dp.add(root);
//    boolean reverse = false;
//
//while(!dp.isEmpty()){
//        int n  = dq.size();
//        if (!reverse) {
//            while(n > 0) {
//                if (dq.peekFirst().left != null) {
//                    dq.offerLast(dp.peekFirst().left);
//                }
//                if (dq.peekFirst().right != null) {
//                    dq.offerLast(dp.peekFirst().right);
//                }
//                System.out.println(dp.pollFirst().value);
//                n--;
//            }
//        } else {
//            while(n > 0) {
//                if (dq.peekLast().right != null) {
//                    dq.offerFirst(dp.peekFirst().right);
//                }
//                if (dq.peekFirst().left != null) {
//                    dq.offerFirst(dp.peekFirst().left);
//                }
//                System.out.println(dp.pollLast().value);
//                n--;
//            }
//        }
//        reverse = !reverse;
//    }
//
//    bullmq
//    node-cache

//    BFS :
}
