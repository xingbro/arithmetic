package tree;

/**
 * 树（主要是二叉树）具有的一些性质
 * @Author lx
 * @Date 2020/11/24 13:02
 * @Version 1.0
 */
public class Nature {

    /**
     * 以 root 为根节点的二叉树的最大深度
     *
     * @param root
     * @return
     */
    public static int MaxDepth(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        int left = MaxDepth(root.left);
        int right = MaxDepth(root.right);
        return left > right ? ++left : ++right;
    }

    /**
     * 以 root 为根节点的二叉树的最大深度
     * 当左右子树高度差大于 1 时返回 -1
     *
     * @param root
     * @return
     */
    public static int MaxHeight(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        int left = MaxHeight(root.left);
        if (left == -1) {
            return -1;
        }
        int right = MaxHeight(root.right);
        if (right == -1) {
            return -1;
        }
        int diff = left - right;
        return diff > 1 ? - 1 : (diff < - 1 ? -1 : (diff > 0 ? ++left : ++right));
    }

    /**
     * 判断以 root 为根节点的二叉树，是否是平衡的
     *
     * @param root
     * @return
     */
    public static boolean isBalanced(BinaryTree root) {
        return MaxHeight(root) != -1;
    }
}
