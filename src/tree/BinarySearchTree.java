package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author lx
 * @Date 2020/9/30 10:43
 * @Version 1.0
 */
public class BinarySearchTree {

    public BinarySearchTree left;

    public BinarySearchTree right;

    public int val;

    public BinarySearchTree(int val) {
        this.val = val;
    }

    /**
     * 前序遍历
     * 辅助栈
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal1(BinarySearchTree root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {return list;}
        LinkedList<BinarySearchTree> stack = new LinkedList<>();
        stack.add(root);
        while (stack.size() > 0) {
            BinarySearchTree last = stack.pollLast();
            list.add(last.val);
            if (last.right != null) {
                stack.add(last.right);
            }
            if (last.left != null) {
                stack.add(last.left);
            }
        }
        return list;
    }

    /**
     * 前序遍历
     * 递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(List<Integer> list, BinarySearchTree root) {
        if (root == null) {
            return list;
        }
        list.add(root.val);
        if (root.left != null) {
            preorderTraversal2(list, root.left);
        }
        if (root.right != null) {
            preorderTraversal2(list, root.right);
        }
        return list;
    }

    /**
     * 中序遍历
     * 辅助栈
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal1(BinarySearchTree root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<BinarySearchTree> stack = new LinkedList<>();
        BinarySearchTree cur = root;
        while (stack.size() > 0 || cur != null) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }
            BinarySearchTree last = stack.pollLast();
            list.add(last.val);
            cur = last.right;
        }
        return list;
    }

    /**
     * 中序遍历
     * 递归
     * @param list
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(List<Integer> list, BinarySearchTree root) {
        if (root == null) {
            return list;
        }
        if (root.left != null) {
            inorderTraversal2(list, root.left);
        }
        list.add(root.val);
        if (root.right != null) {
            inorderTraversal2(list, root.right);
        }
        return list;
    }

    /**
     * 后序遍历
     * 辅助栈
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal1(BinarySearchTree root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        LinkedList<BinarySearchTree> stack1 = new LinkedList<>();
        LinkedList<BinarySearchTree> stack2 = new LinkedList<>();
        stack1.add(root);
        while (stack1.size() > 0) {
            BinarySearchTree last = stack1.pollLast();
            stack2.add(last);
            if (last.left != null) {
                stack1.add(last.left);
            }
            if (last.right != null) {
                stack1.add(last.right);
            }
        }
        while (stack2.size() > 0) {
            list.add(stack2.pollLast().val);
        }
        return list;
    }

    /**
     * 后序遍历
     * 递归
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(List<Integer> list, BinarySearchTree root) {
        if (root == null) {
            return list;
        }
        if (root.left != null) {
            postorderTraversal2(list, root.left);
        }
        if (root.right != null) {
            postorderTraversal2(list, root.right);
        }
        list.add(root.val);
        return list;
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(BinarySearchTree root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        LinkedList<BinarySearchTree> queue = new LinkedList<>();
        queue.add(root);
        int size;
        while ((size = queue.size()) > 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                BinarySearchTree first = queue.pollFirst();
                list.add(first.val);
                if (first.left != null) {
                    queue.add(first.left);
                }
                if (first.right != null) {
                    queue.add(first.right);
                }
            }
            lists.add(list);
        }
        return lists;
    }

}
