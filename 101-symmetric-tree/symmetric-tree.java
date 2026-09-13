class Solution {
    public boolean isSymmetric(TreeNode root) {
        return mirror(root.left, root.right);
    }

    public boolean mirror(TreeNode left, TreeNode right) {

        // Both are empty
        if (left == null && right == null) {
            return true;
        }

        // One is empty, other is not
        if (left == null || right == null) {
            return false;
        }

        // Values must be equal
        if (left.val != right.val) {
            return false;
        }

        // Compare opposite sides
        return mirror(left.left, right.right) &&
               mirror(left.right, right.left);
    }
}