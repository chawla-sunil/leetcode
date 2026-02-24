package org.example.top_interview_questions;

import java.util.HashMap;
import java.util.Map;

public class LC105ConstructBinaryTreeFromPreorderAndInorderTraversal {
//    Given two integer arrays preorder and inorder where preorder is the preorder traversal of a
//    binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
//
//            Example 1:
//    Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//    Output: [3,9,20,null,null,15,7]
//
//    Example 2:
//    Input: preorder = [-1], inorder = [-1]
//    Output: [-1]
//
//
//    Constraints:
//
//    1 <= preorder.length <= 3000
//    inorder.length == preorder.length
//    -3000 <= preorder[i], inorder[i] <= 3000
//    preorder and inorder consist of unique values.
//    Each value of inorder also appears in preorder.
//    preorder is guaranteed to be the preorder traversal of the tree.
//    inorder is guaranteed to be the inorder traversal of the tree.


    // Both solutions give exact same time on leetcode.
    /**
     * Constructs a binary tree from preorder and inorder traversal arrays.
     *
     * Understanding 'low' and 'high':
     * - YES, 'low' and 'high' ARE array boundaries, but specifically for the INORDER array
     * - They define the valid range in the inorder array that can be used to construct the current subtree
     * - Initially: low = 0, high = n-1 (entire inorder array is valid)
     *
     * How low and high work:
     * 1. 'low' = leftmost valid index in inorder for current subtree
     * 2. 'high' = rightmost valid index in inorder for current subtree
     * 3. When low > high, it means there are no elements left to process (base case)
     *
     * Key Algorithm Steps:
     * - pIndex is a global counter that tracks current position in preorder array
     * - Each call to makeTree processes one node from preorder (incrementing pIndex)
     * - The inIndex (position of current root in inorder) acts as the "pivot" that divides the array
     *
     * How we decide low and high for left and right subtrees:
     *
     * For LEFT subtree:
     *   - low remains SAME (left boundary doesn't change)
     *   - high becomes (inIndex - 1) because everything left of root is left subtree
     *   - Range: [low, inIndex-1]
     *
     * For RIGHT subtree:
     *   - low becomes (inIndex + 1) because everything right of root is right subtree
     *   - high remains SAME (right boundary doesn't change)
     *   - Range: [inIndex+1, high]
     *
     * Example: inorder = [9, 3, 15, 20, 7], preorder = [3, 9, 20, 15, 7]
     *
     * Initial call: makeTree(preorder, map, 0, 4)
     *   - pValue = 3 (pIndex=0), inIndex = 1
     *   - Left subtree: makeTree(preorder, map, 0, 0) -> processes 9
     *   - Right subtree: makeTree(preorder, map, 2, 4) -> processes 20, 15, 7
     *
     * Key Difference from approach buildTree2:
     * - Previous: Passed both preorder boundaries (preStart, preEnd) AND inorder boundaries(inStart, inEnd)
     * - Current: Uses global pIndex to traverse preorder sequentially, only tracks inorder boundaries
     * - Both approaches are correct; this one is slightly simpler with the global counter
     *
     * Time Complexity: O(n) - each node processed once
     * Space Complexity: O(n) - HashMap + recursion stack
     */

    int pIndex = 0;
    // p = preorder
    // in = inorder
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }

        return makeTree(preorder, map, 0, n - 1);
    }

    private TreeNode makeTree(int[] preorder, Map<Integer, Integer> map, int low, int high) {
        if (low > high) {
            // low = inStart = start of the inorder array
            // high = inEnd = end of the inorder array
            return null;
        }
        // p = preorder
        // in = inorder

        int pValue = preorder[pIndex];
        int inIndex = map.get(pValue);

        TreeNode root = new TreeNode(pValue);
        pIndex++;

        root.left = makeTree(preorder, map, low, inIndex - 1);
        root.right = makeTree(preorder, map, inIndex + 1, high);
        return root;
    }


    /**
     * Constructs a binary tree from preorder and inorder traversal arrays.
     *
     * Algorithm Overview:
     * - Preorder traversal visits nodes in the order: Root -> Left -> Right
     * - Inorder traversal visits nodes in the order: Left -> Root -> Right
     *
     * Key Insights:
     * 1. The first element in preorder is always the root of the current subtree
     * 2. Once we find the root in inorder, elements to its left form the left subtree
     *    and elements to its right form the right subtree
     * 3. rootIndex acts as the "mid" point - it's the index of the current root in inorder array
     *    - All nodes left of rootIndex belong to the left subtree
     *    - All nodes right of rootIndex belong to the right subtree
     *
     * Optimization:
     * - We use a HashMap to store inorder indices for O(1) lookup instead of O(n) search
     * - We don't actually slice/copy arrays; instead we use start/end pointers to
     *   define boundaries of subarrays, making the algorithm space-efficient
     *
     * Process:
     * 1. Create a map of inorder values to their indices for fast lookup
     * 2. Pick the first element from preorder as root
     * 3. Find its position (rootIndex) in inorder - this divides the array
     * 4. Calculate left subtree size: rootIndex - inStart
     * 5. Recursively build left subtree using appropriate boundaries
     * 6. Recursively build right subtree using remaining boundaries
     *
     * Time Complexity: O(n) where n is the number of nodes
     * Space Complexity: O(n) for the hashmap and recursion stack
     */
    // Video link for understanding this solution : https://www.youtube.com/watch?v=ihj4IQGZ2zc
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        // Create a map to store the index of each value in inorder
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return helper2(preorder, inorderMap, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode helper2(int[] preorder, Map<Integer, Integer> inorderMap,
                             int preStart, int preEnd, int inStart, int inEnd) {
        // Base case: if there are no elements to construct the tree
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // The first element in preorder is the root
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // Find the index of the root in inorder using the map
        int rootIndex = inorderMap.get(rootVal);

        // Number of elements in the left subtree
        int leftSize = rootIndex - inStart;

        // Recursively build the left and right subtrees
        root.left = helper2(preorder, inorderMap, preStart + 1, preStart + leftSize, inStart, rootIndex - 1);
        root.right = helper2(preorder, inorderMap, preStart + leftSize + 1, preEnd, rootIndex + 1, inEnd);

        return root;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
