
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC103BinaryTreeZigzagLevelOrderTraversal {
//    Given the root of a binary tree, return the zigzag level order traversal of its nodes' values.
//    (i.e., from left to right, then right to left for the next level and alternate between).
//
//    Example 1:
//    Input: root = [3,9,20,null,null,15,7]
//    Output: [[3],[20,9],[15,7]]
//
//    Example 2:
//    Input: root = [1]
//    Output: [[1]]
//
//    Example 3:
//    Input: root = []
//    Output: []
//
//
//    Constraints:
//
//            The number of nodes in the tree is in the range [0, 2000].
//            -100 <= Node.val <= 100


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> levelValues = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    levelValues.add(node.val);
                } else {
                    levelValues.add(0, node.val); // Insert at the beginning for right-to-left traversal
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            ans.add(levelValues);
            leftToRight = !leftToRight;
        }

        return ans;
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
