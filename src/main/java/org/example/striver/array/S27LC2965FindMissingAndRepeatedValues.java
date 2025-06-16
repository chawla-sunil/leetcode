package org.example.striver.array;

public class S27LC2965FindMissingAndRepeatedValues {
//    You are given a 0-indexed 2D integer matrix grid of size n * n with values in the range [1, n2].
//    Each integer appears exactly once except a which appears twice and b which is missing.
//    The task is to find the repeating and missing numbers a and b.
//
//    Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b.
//
//
//    Example 1:
//    Input: grid = [[1,3],[2,2]]
//    Output: [2,4]
//    Explanation: Number 2 is repeated and number 4 is missing so the answer is [2,4].
//
//    Example 2:
//    Input: grid = [[9,1,7],[8,9,2],[3,4,6]]
//    Output: [9,5]
//    Explanation: Number 9 is repeated and number 5 is missing so the answer is [9,5].

    // XOR solution
    // Explaination: https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers/
    // https://youtu.be/2D0D8HE6uak?si=IfpX7ef59T1wFS_S
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int n2 = n * n;

        int xor = 0;

        int i = 1;
        for (int[] row: grid) {
            for (int num: row) {
                xor = xor ^ num;
                xor = xor ^ i;
                i++;
            }
        }
        // now we have the xor.
        // xor gives true or 1 only if two bit are different.
        // that means xor of a and b, we will find out the LSB(least Significient Bit) (first 1 from the right)
        // for example, 12 => 1100,    LSB is first 1 from the right which is 100 which number 4 in decimal.

        int rightMostSetBit = xor & ~(xor - 1);
        // xr - 1 => Subtracting 1 from xr flips all bits from the least significant 1 to the right.
        // ~(xr - 1) => This negates all the bits of xr - 1.
        // xr & ~(xr - 1) => This isolates the rightmost 1 bit in xr.
        // Example xor = 1100
        // xor - 1 = 1011
        // ~(xor - 1) = 0100
        // xor & ~(xor - 1) = 1100 & 0100 = 0100 (which is 4)
        // So the result is 4, which corresponds to the rightmost 1 bit in 1100. (rightMostSetBit)

        int zeroGroup = 0;
        int oneGroup = 0;

        for (int[] row: grid) {
            for (int num: row) {
                if ((num & rightMostSetBit) == 0) {
                    // zeroGroup
                    zeroGroup = zeroGroup ^ num;
                } else {
                    // oneGroup
                    oneGroup = oneGroup ^ num;
                }
            }
        }

        for (int j = 1; j <= n2; j++) {
            if ((j & rightMostSetBit) == 0) {
                zeroGroup ^= j;
            } else {
                oneGroup ^= j;
            }
        }

        int count = 0;

        for (int[] row: grid) {
            for (int num: row) {
                if (num == zeroGroup) {
                    count++;
                }
            }
        }

        if (count > 0) {
            return new int[] {zeroGroup, oneGroup};
        }

        return new int[] {oneGroup, zeroGroup};
    }

    // mathematical solution
    public int[] findMissingAndRepeatedValues2(int[][] grid) {
        // some test cases will fail, if we don't use long for sum n and of squrares.
        int n = grid.length;
        int nIntoN = n * n;

        long SN = (long) nIntoN * (nIntoN+1) / 2; // sum of n numbers
        long S2N = (long) nIntoN * (nIntoN+1) * (2*nIntoN + 1) / 6; // sum of 1 to N squares
        // here in all places 2 represents power , which square.
        long S = 0;
        long S2 = 0;

        for (int[] row: grid) {
            for (int i: row) {
                S += i;
                S2 += (long) (i*i);
            }
        }

        long aMinusB = S - SN; // = a-b;                     _____equation-1
        long a2MinusB2 = S2 - S2N; // = a^2 - b^2 = (a-b)(a+b) = (aMinusB)(a+b);

        long aPlusB = a2MinusB2 / aMinusB; // = (a+b) = (a^2 - b^2) / (a-b);    _____equation-2

        // adding equation1 and equation 2
        long a = (aMinusB + aPlusB) / 2;

        // from equation1, we can say that, b = a - aMinusB; = a - (a-b);
        long b = a - aMinusB; // or = (aMinusB + aPlusB) / 2;

        return new int[] {(int) a, (int) b};
    }
}
