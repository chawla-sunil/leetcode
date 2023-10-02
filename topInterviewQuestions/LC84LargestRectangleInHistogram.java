
import java.util.Stack;

public class LC84LargestRectangleInHistogram {
//    Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
//    return the area of the largest rectangle in the histogram.
//
//    Example 1:
//    Input: heights = [2,1,5,6,2,3]
//    Output: 10
//    Explanation: The above is a histogram where width of each bar is 1.
//    The largest rectangle is shown in the red area, which has an area = 10 units.
//
//            Example 2:
//    Input: heights = [2,4]
//    Output: 4
//
//    Constraints:
//
//            1 <= heights.length <= 10^5
//            0 <= heights[i] <= 10^4

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> s = new Stack<>();

        // Nearest Smaller element to left
        int[] NSL = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.isEmpty()) {
                NSL[i] = -1;
            } else if (heights[s.peek()] < heights[i]) {
                NSL[i] = s.peek();
            } else {
                while(s.size() > 0 && heights[s.peek()] >= heights[i]) {
                    s.pop();
                }
                if (s.isEmpty()) {
                    NSL[i] = -1;
                } else {
                    NSL[i] = s.peek();
                }
            }
            s.push(i);
        }
        s.clear();

        // Nearest smaller element to right
        int[] NSR = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (s.isEmpty()) {
                NSR[i] = n;
            } else if (heights[s.peek()] < heights[i]) {
                NSR[i] = s.peek();
            } else {
                while(!s.isEmpty() && heights[s.peek()] >= heights[i]) {
                    s.pop();
                }

                if (s.isEmpty()) {
                    NSR[i] = n;
                } else {
                    NSR[i] = s.peek();
                }
            }
            s.push(i);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            int curr = (NSR[i] - NSL[i] - 1) * (heights[i]);
            if (curr > max) {
                max = curr;
            }
        }
        return max;
    }

    // Better Solution than above
    public static int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // = NSL = idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // = NSR = idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }

        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }

        return maxArea;
    }
}
