
import java.util.*;

public class Greenlight {

//    Greenlight, a company providing a debit card for kids with parental controls and financial education features, wants to empower young users to make smart spending choices while staying within their budget. They decide to launch the "Greenlight Budgeting Challenge - Smart Choices" to encourage kids to plan their spending and find the best combination of purchases that fit within their allocated budget.
//    The Challenge: Each participant is given a virtual Greenlight debit card with a preloaded balance (K) and a list of available items to purchase (A[]), each with its respective cost. The challenge is to find all unique quadruples of items from the given list whose total cost equals the balance on their Greenlight debit card.
//
//            Requirement 1:
//    Start with 1 quadruple,
//
//    Optional Requirement:
//    Find all quadruple, with Duplicates
//
//
//    Example:
//
//    Input: array = {10, 2, 3, 4, 5, 9, 7, 8}
//    X = 23 / 21
//    Output: 3 5 7 8
//    Sum of output is equal to 23,
//    i.e. 3 + 5 + 7 + 8 = 23.
//
//
//    Input: array = {1, 2, 3, 4, 5, 9, 7, 8}
//    X = 16
//    Output: 1 3 5 7
//    Sum of output is equal to 16,
//    i.e. 1 + 3 + 5 + 7 = 16.

    public static void main(String[] args) {
        int[] test = new int[]  {10, 2, 3, 4, 5, 9, 7, 8};
        List<List<Integer>> result = quad(test, 23);
        System.out.println(result);
    }

    public static List<List<Integer>> quad(int[] A, int target) {
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(A);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < A.length - 4; i++) {
            for (int j = i +1; j < A.length - 3; j++) {
                int need = target - (A[i] + A[j]);

                int left = j + 1;
                int right = A.length - 1;

                while (left < right) {
                    if (A[left] + A[right] == need) {
                        set.add(Arrays.asList(A[i], A[j], A[left], A[right]));
                        left++;
                        right--;
                    } else if (A[left] + A[right] < need) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        result.addAll(set);
        return result;
    }





//   question 2

//        Given an infinite stream of characters and a list L of strings, create a function that prints the when a word in L is recognized during the processing of the stream.
//    Example: L = ["ok","test","one","try","trying"]
//    stream = a,b,c,o,k,d,e,f,t,r,y,i,n,g.............
//    Output:
//    Ok
//            Try
//    Trying
//
//
//-> function that adds the word to the tree fn (String word)  - generates tree
//    You don’t need to call the function multiple times to create the entire tree
//
//
//-> fn(String word)   - check whether that word exists

    //https://gist.github.com/shyamalaLokre/19594961309dcd6d04ee

    class Node {
        Character c;
        Map<Character, Node> next;

        public Node(Character c) {
            this.c = c;
            next = new HashMap<>();
        }

        public Node addNext(Character c) {
            if (next.get(c) == null) {
                next.put(c, new Node(c));
            }
            return next.get(c);
        }
    }


}
