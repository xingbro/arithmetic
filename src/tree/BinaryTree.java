package tree;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 二叉树
 *
 * @Author lx
 * @Date 2020/9/30 10:43
 * @Version 1.0
 */
public class BinaryTree {

    public BinaryTree left;

    public BinaryTree right;

    public int val;

    public BinaryTree(int val) {
        this.val = val;
    }

    /**
     * 根据层序遍历结果构建二叉树（默认完全二叉树）
     *
     * @param nums
     * @return
     */
    public static BinaryTree getBinaryTreeByLevel(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        BinaryTree root = new BinaryTree(nums[0]);
        LinkedList<BinaryTree> queue = new LinkedList<>();
        queue.add(root);
        int lengthPoint = 1;
        while (queue.size() > 0) {
            BinaryTree first = queue.pollFirst();
            if (lengthPoint < nums.length) {
                root.left = new BinaryTree(nums[lengthPoint++]);
                queue.add(root.left);
            }
            if (lengthPoint < nums.length) {
                root.right = new BinaryTree(nums[lengthPoint++]);
                queue.add(root.right);
            }
        }
        return root;
    }

    /**
     * 根据前序、中序遍历结果，构建二叉树（树中没有重复元素）
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public static BinaryTree getBinaryTreeByPreAndIn(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length < 1 || inorder.length < 1) {
            return null;
        }
        // 根据前序遍历中元素的值，直接能够找到在中序中的位置
        HashMap<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    /**
     * 递归拼接二叉树
     *
     * @param preorder
     * @param pLeft
     * @param pRight
     * @param inorder
     * @param ileft
     * @param iRight
     * @param map
     * @return
     */
    private static BinaryTree helper(int[] preorder, int pLeft, int pRight,
                                     int[] inorder, int ileft, int iRight, HashMap<Integer, Integer> map) {
        /**
         * 根据两个数组构建二叉树
         * 前序的第一个是 根节点，通过这个 根节点 去中序遍历结果中找，所在位置为左右子树分界点
         * 这样在前序遍历中，根节点之后，数出左子树中元素的个数，就是左子树的前序，后边剩下的是右子树的前序
         * 递归解决
         * 例：
         *       4
         *    2    6
         *  1  3  5  7
         *
         * 4,2,1,3,6,5,7
         *
         * 1,2,3,4,5,6,7
         */
        // [1] 终止条件，前序遍历中只有一个节点
        if (pLeft > pRight) {
            return null;
        }
        BinaryTree root = new BinaryTree(preorder[pLeft]);
        if (pLeft == pRight) {
            return root;
        }
        // [2] 正常迭代
        int inHeadNum = map.get(preorder[pLeft]);
        // 前序遍历结果中拆分出的长度，由中序遍历中根节点所在的位置确定
        root.left = helper(preorder, pLeft + 1, pLeft + (inHeadNum - ileft), inorder, ileft, inHeadNum - 1, map);
        root.right = helper(preorder, pRight - (iRight - inHeadNum) + 1, pRight, inorder, inHeadNum + 1, iRight, map);
        // [3] 正常迭代返回值
        return root;
    }
}
