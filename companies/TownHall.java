
import java.util.ArrayList;
import java.util.List;

public class TownHall {
    static int count;
    public static void main(String[] args) {
        int[] arr = {3,3,4,5,5,6,8,10,12,13,15};
        findPythagores(arr, arr.length);

        int[] arr2 = {1,2,3};
        printPermutations(arr2);
    }

    public static void findPythagores(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] * arr[i];
        }

        for (int i = n - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (arr[left] + arr[right] == arr[i]) {
                    System.out.println(Math.sqrt(arr[left]) + "-" + Math.sqrt(arr[right]) + "-" + Math.sqrt(arr[i]));
                    if (arr[left] == arr[left+1]) {
                        left++;
                    } else if (arr[right] == arr[right-1]) {
                        right--;
                    } else {
                        left++;
                        right--;
                    }
                } else if (arr[left] + arr[right] < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }
    }

    public static void printPermutations(int[] arr) {
        backTracing(arr, new ArrayList<>());
        System.out.println(count);
    }

    public static void backTracing(int[] arr, List<Integer> currList) {
        if (currList.size() == arr.length) {
            count++;
            System.out.println(currList);
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (currList.contains(arr[i])) {
                    continue;
                }
                currList.add(arr[i]);
                backTracing(arr, currList);
                currList.remove(currList.size() - 1);
            }
        }
    }
}
