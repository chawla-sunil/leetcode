package org.example.two_pointers;

public class LC42TrappingRainWater {
//    Given n non-negative integers representing an elevation map where the width of each bar is 1,
//    compute how much water it can trap after raining.
//
//
//    Example 1:
//    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//    Output: 6
//    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
//    In this case, 6 units of rain water (blue section) are being trapped.
//
//    Example 2:
//    Input: height = [4,2,0,3,2,5]
//    Output: 9
//
//
//    Constraints:
//
//    n == height.length
//    1 <= n <= 2 * 104
//    0 <= height[i] <= 105

    // good solution understanding wise, but uses extra spaces.
    // Here we the stored water a index i is calculated based on what is the max hight on left side and right side
    // so we will left the max of left and right side and then calculate the difference from max to current index value
    public int trap(int[] height) {
        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        maxLeft[0] = height[0];
        for(int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
        }


        maxRight[n-1] = height[n-1];
        for (int i = n -2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i]);
        }
        // System.out.println(Arrays.toString(height));
        // System.out.println(Arrays.toString(maxLeft));
        // System.out.println(Arrays.toString(maxRight));
        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }

        return water;
    }

    // trap2 and trap3 are almost same solution.
    public int trap2(int[] h) {
        int left = 0;
        int right = h.length - 1;
        int leftMax = h[left];
        int rightMax = h[right];
        int ans = 0;

        while (left < right) {
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, h[left]);
                ans += leftMax - h[left];
            } else {
                right--;
                rightMax = Math.max(rightMax, h[right]);
                ans += rightMax - h[right];
            }
        }
        return ans;
    }

    public int trap3(int[] h) {
        int l = 0, r = h.length - 1, lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE, ans = 0;
        while (l < r) {
            lmax = Math.max(lmax, h[l]);
            rmax = Math.max(rmax, h[r]);
            ans += (lmax < rmax) ? lmax - h[l++] : rmax - h[r--];
        }
        return ans;
    }
}
