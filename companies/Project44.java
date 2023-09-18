
public class Project44 {
//    Q .
//    Ada the Ladybug lives near an orange tree. Instead of reading books, she investigates the oranges. The oranges on orange tree can be in up to 5*50 Shades of Orange. She walks from orange to orange, examining different properties of orange tree. The oranges are connected by branches. There is more oranges then branches, yet it is still possible to get from any orange to any other orange [through branches]. The tree is rooted.
//    Ada has many questions in following form: She goes from orange A to orange B (by shortest path) and is interested in total number different Shades of Orange among all subtrees of all edges on shortest path.
//    Input
//    The first line of input consists of 1 ≤ T ≤ 100, the number of test-cases.
//    The first line of each test case contains three integers 1 ≤ N, Q ≤ 4.5*105, 0 ≤ R < N, the number of oranges, the number of questions and number of root.
//    Next line contains N integers 1 ≤ Si ≤ 250, the shade of orange of orange i.
//    Next N-1 lines contains two integers 0 ≤ I, J < N, I ≠ J , the numbers of oranges which are connected by branch.
//    Next Q lines contains two integers 0 ≤ A, B < N, the path Ada is interested about.
//    The sum of all N and all Q among all test-cases won't exceed 106
//    Output
//    For each question answer the number of shades in all subtrees of all nodes on shortest path from A to B.
//            Example Input
//1
//        10 7 1
//        1 2 1 4 5 6 6 8 9 9
//        0 9
//        9 3
//        3 4
//        4 6
//        4 5
//        4 8
//        1 3
//        1 2
//        2 7
//        4 4
//        8 6
//        0 6
//        7 0
//        7 2
//        0 0
//        2 3
//
//    Example Output
//        3
//        3
//        5
//        7
//        2
//        1
//        7
//
//    Explanation - shades in subtrees
//        5 6 9
//        5 6 9
//        1 4 5 6 9
//        1 2 4 5 6 8 9
//        1 8
//        1
//        1 2 4 5 6 8 9
//    question link btw
//    I was not able to solve it in 30 minutes.
//    https://www.spoj.com/problems/ADAORANG/


    // 2nd question was design question
    // design parkingLot

    // I was stopped after writing this much, he said you can design.

//    Design Parking Lot
//
//    ParkingMain{
//        Map<Integer, Slot> map = new HashMap<>();
//    }
//    Vehicle {
//        String number;
//        String color;
//    }
//    Slot {
//        int id;
//        Vehicle vehicle;
//
//    }
//    ParkingLotAction{
//
//    }
//    ParkingLotService {
//        ParkingLotResponse createParkingLot(int n) {}
//        ParkingLotresponse park(ParkingLotRequest request) {}
//        ParkingLotresponse leave\
//        (ParkingLotRequest request) {}
//
//    }
//
//
//}
//    ParkingLotResponse{
//        String message;
//    }
//
//    ParkingLotRequest{
//        Map<Integer, Slot> map;
//        String[] data;
//    }


//    DSA question 2
//    Given a string s, return the longest palindromic substring in s.
//
//    Input: s = "babad"
//    Output: "bab"
//    Explanation: "aba" is also a valid answer.

    int max = 0;
    int start = 0;

    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        for (int i = 0; i < s.length(); i++) {
            checkLeftRight(s, i, i); // for odd
            checkLeftRight(s, i, i + 1); // for even
        }
        return s.substring(start, start + max);
    }

    public void checkLeftRight(String s, int i, int j) {
        while (j < s.length() && i >= 0 && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        if (max < j - 1 - i) {
            max = j - 1 - i;
            start = i + 1;

        }
    }
}
