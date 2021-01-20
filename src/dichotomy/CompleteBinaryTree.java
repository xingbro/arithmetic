package dichotomy;

import tree.BinaryTree;

/**
 * 二分法在完全二叉树中的一个使用
 *
 * @Author lx
 * @Date 2021/1/20 10:20
 * @Version 1.0
 */
public class CompleteBinaryTree {

    /**
     * 一个完全二叉树中节点的个数
     *
     * @param root
     * @return
     */
    public int countNodes(BinaryTree root) {
        if (root == null) {
            return 0;
        }
        BinaryTree node = root;
        int level = 0;
        while (node.left != null) {
            node = node.left;
            ++level;
        }
        int low = 1 << level, high = 1 << level + 1;
        while (low < high) {
            int mid = (low & high) + ((low ^ high) >> 1);
            if (isExist(root, level, mid)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low - 1;
    }

    /**
     * 判断 树(root) 中是否含有第 k 个节点
     *
     * @param root
     * @param level
     * @param k
     * @return
     */
    public boolean isExist(BinaryTree root, int level, int k) {
        /**
         * 完全二叉树中（图示为满二叉树）
         *                               1                                   .........level = 0
         *                10                              11                 .........level = 1
         *       100             101             110            111          .........level = 2
         *  1000    1001    1010    1011    1100    1101    1110    1111     .........level = 3
         *                              ......
         * level 层有 level + 1 位
         * 第 k 个节点，k 的二进制表示中，从左边的第一个有效数位 1 开始数，下一位是 0 表示在往左节点走
         *
         */
        int bits = 1 << level - 1;
        BinaryTree node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }
}
