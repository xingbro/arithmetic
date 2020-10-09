package tree;

/**
 * 红黑树
 *
 * @Author lx
 * @Date 2020/9/28 8:52
 * @Version 1.0
 */
public class RedBlackTree {

    /**
     * 使用 (TreeNode) root 代表这棵树
     */
    static class TreeNode {
        TreeNode parent;
        TreeNode left;
        TreeNode right;
        int val;
        boolean red;

        public TreeNode(int val) {
            this.val = val;
        }

        /**
         * 左旋
         * 此方法为《算法导论》中伪代码实现
         * 仅作为原理参考
         *
         * @param root
         * @param x
         * @return
         */
        public TreeNode leftRotate(TreeNode root, TreeNode x) {
            TreeNode y = x.right;
            // x & xr
            x.right = y.left;
            if (y.left != null) {
                y.left.parent = x;
            }
            // y & yp
            y.parent = x.parent;
            if (x.parent == null) {
                root = y;
            } else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
            // x & y
            y.left = x;
            x.parent = y;
            return root;
        }

        /**
         * 右旋
         *
         * @param root
         * @param x
         * @return
         */
        public TreeNode rightRotate(TreeNode root, TreeNode x) {
            /**
             * 是将 x 旋转到右孩子位置
             */
            TreeNode y = x.left;
            x.left = y.right;
            if (y.right != null) {
                y.right.parent = x;
            }
            y.parent = x.parent;
            if (x.parent == null) {
                root = y;
            } else if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
            y.right = x;
            x.parent = y;
            return root;
        }

        /**
         * 《算法导论》
         * 换位，将 v 换到 u 所在的位置
         * 只是换与父节点的关系
         * @param root
         * @param u
         * @param v
         * @return
         */
        public TreeNode transplant(TreeNode root, TreeNode u, TreeNode v) {
            if (u.parent == null) {
                root = v;
            } else if (u == u.parent.left) {
                u.parent.left = v;
            } else {
                u.parent.right = v;
            }
            v.parent = u.parent;
            return root;
        }

        /**
         * 《算法导论》
         * 找到最小值，以传入节点为根节点
         *
         * @param x
         * @return
         */
        public TreeNode minimum(TreeNode x) {
            while (x.left != null) {
                x = x.left;
            }
            return x;
        }

        /**
         * 《算法导论》
         *
         * @param root
         * @param z
         * @return
         */
        public TreeNode insertNode(TreeNode root, TreeNode z) {
            TreeNode x = root, y = null;
            while (x != null) {
                y = x;
                if (z.val < x.val) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
            z.parent = y;
            if (y == null) {
                root = z;
            } else if (z.val < y.val) {
                y.left = z;
            } else {
                y.right = z;
            }
            z.red = true;
            return redInsertBalance(root, z);
        }

        /**
         * 插入后平衡
         * 基于《算法导论》
         * 只是主体逻辑，帮助理解，并未考虑空指针等情况
         *
         * @param root
         * @param x
         * @return
         */
        public TreeNode redInsertBalance(TreeNode root, TreeNode x) {
            TreeNode y;
            while (x.parent.red) {
                if (x.parent == x.parent.parent.left) {
                    y = x.parent.parent.right;
                    if (y.red) {
                        x.parent.red = false;
                        y.red = false;
                        x.parent.parent.red = true;
                        x = x.parent.parent;
                    } else if (x == x.parent.right) {
                        x = x.parent;
                        root = leftRotate(root, x);
                    }
                    x.parent.red = false;
                    x.parent.parent.red = true;
                    root = rightRotate(root, x.parent.parent);
                } else {
                    y = x.parent.parent.left;
                    if (y.red) {
                        x.parent.red = false;
                        y.red = false;
                        x.parent.parent.red = true;
                        x = x.parent.parent;
                    } else if (x == x.parent.left) {
                        x = x.parent;
                        root = rightRotate(root, x);
                    }
                    x.parent.red = false;
                    x.parent.parent.red = true;
                    root = leftRotate(root, x.parent.parent);
                }
            }
            root.red = false;
            return root;
        }

        /**
         * 《算法导论》
         * 删除
         * 删除一定是要在整个树结构中，去掉一个位置（这与，元素在树结构中移动是两回事）
         * 1.如果 z 本身有两个子节点，那么删除 z 之后，z 所占据这个位置还在
         * 2.如果 z 本身的子节点个数小于 2 ，那直接把存在的子节点整体移动过来
         *
         * @param root
         * @param z
         * @return
         */
        public TreeNode delete(TreeNode root, TreeNode z) {
            // 参数 x 指向替代者
            TreeNode x;
            // 参数 y 所指向的是要删除的位置
            TreeNode y = z;
            boolean color = y.red;
            if (z.left == null) {
                x = z.right;
                root = transplant(root, z, z.right);
            } else if (z.right == null) {
                x = z.left;
                root = transplant(root, z, z.left);
            } else {
                y = minimum(z.right);
                color = y.red;
                x = y.right;
                if (z == y.parent) {
                    x.parent = y;   // ?
                } else {
                    transplant(root, y, y.right);
                    y.right = z.right;
                    y.right.parent = y;
                }
                transplant(root,z, y);
                y.left = z.left;
                z.left.parent = y;
                y.red = z.red;
            }
            if (!color) {
                deleteFixup(root, x);
            }
            return root;
        }

        /**
         * 《算法导论》
         * 删除后修正
         * @param root
         * @param x
         * @return
         */
        public TreeNode deleteFixup(TreeNode root, TreeNode x) {
            /**
             * 性质 1 ：所有节点要么红色，要么黑色
             * 性质 2 ：根节点为黑色
             * 性质 3 ：所有子叶节点为黑色（这里指的是 T.nil）
             * 性质 4 ：红色节点不能有红色的子节点
             * 性质 5 ：黑高相等
             * 如果被删除的是黑色的，那么那一个分支少了一个黑色节点
             * 替代者是红色，直接将替代者染成黑色即可，无需旋转
             * 看替代者是谁？
             * 1.替代者是左孩子，此时右子节点为空，由性质 4、5 推出，左子树只可能有一个 红色节点
             * 2.替代者是右孩子，且左子树为空，同上，右子树只可能有一个 红色节点
             * 此两者，就是上述只需要将颜色染成黑色即可
             * ======================================================================================
             * 3.替代者是右孩子，且左子树不为空，此时，这个右孩子的左子树为空、右子树至多有一个红节点，
             * 替代者本身可黑可红，无法直接通过一次着色完成平衡
             * 4.替代者是后继节点，这个时候，后继节点要么只有一个红色右子节点本身为黑色，要么没有子节点，
             * 本身为黑色或者红色
             * 5.替代者是空，相当于原来有一个黑的直接删了，这里与 4 中的，后继节点为黑色没有子节点一样
             *
             * 但是不管是什么情况导致的，只要它是删了一个黑的，没有红的来补位的情况都得考虑平衡
             */
            TreeNode w = null;  // 指向 x 的叔节点
            while (x != root && !x.red) {
                // 都得处理一下
                if (x == x.parent.left) {
                    w = x.parent.right;
                    if (w.red) {
                        w.red = false;
                        x.parent.red = true;
                        root = leftRotate(root, x.parent);
                        w = x.parent.right;
                    }
                    if (!w.left.red && !w.right.red) {
                        w.red = true;
                        x = x.parent;
                    } else {
                        if (!w.left.red) {
                            w.left.red = false;
                            w.red = true;
                            rightRotate(root, w);
                            w = x.parent.right;
                        }
                        w.red = x.parent.red;
                        x.parent.red = false;
                        w.right.red = false;
                        leftRotate(root, x.parent);
                        x = root;
                    }
                } else {

                }
            }
            // 要是一直抛到根节点，或者本身替代者 x 就是红色
            x.red = false;
            return root;
        }

        /*============================================================================================*/

        /**
         * 左旋：将自己旋转到树结构中，现在这个位置的左子节点位置
         * 此方法抄袭 jdk 1.8 中 HashMap 的红黑树实现
         *
         * @param root
         * @param p
         * @return
         */
        public TreeNode rotateLeft(TreeNode root, TreeNode p) {
            TreeNode r, pp, rl; // p & rl, pp & r, p & r 之间的关系
            if (p != null && (r = p.right) != null) {
                if ((rl = p.right = r.left) != null) {
                    rl.parent = p;
                }
                if ((pp = r.parent = p.parent) == null) {
                    (root = r).red = false;
                } else if (p == pp.left) {
                    pp.left = r;
                } else {
                    pp.right = r;
                }
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        /**
         * 右旋
         *
         * @param root
         * @param p
         * @return
         */
        public TreeNode rotateRight(TreeNode root, TreeNode p) {
            TreeNode l, pp, lr;
            if (p != null && (l = p.left) != null) {
                if ((lr = p.left = l.right) != null) {
                    lr.parent = p;
                }
                if ((pp = l.parent = p.parent) == null) {
                    (root = l).red = false;
                } else if (p == pp.left) {
                    pp.left = l;
                } else {
                    pp.right = l;
                }
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        /**
         * 基于《算法导论》
         * 可配合 Hashmap 中的插入后平衡及旋转使用
         *
         * @param root
         * @param z
         * @return
         */
        public TreeNode insertion(TreeNode root, TreeNode z) {
            TreeNode x = root, y = null;
            while (x != null) {
                y = x;
                if (z.val < x.val) {
                    x = x.left;
                } else {
                    x = x.right;
                }
            }
            z.parent = y;
            if (y == null) {
                (root = z).red = false;
                return root;
            } else if (z.val < y.val) {
                y.left = z;
            } else {
                y.right = z;
            }
            return balanceInsertion(root, z);
        }

        /**
         * 红黑树插入后平衡
         * 源自 HashMap
         *
         * @param root
         * @param x
         * @return
         */
        public TreeNode balanceInsertion(TreeNode root, TreeNode x) {
            x.red = true;
            for (TreeNode xp, xpp, xppl, xppr; ; ) {   // 整明白这几个节点之间的关系
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                } else if (!xp.red || (xpp = xp.parent) == null) {
                    // 如果因为 || 后边的条件进入代码块，root 是红？
                    // 还是主要为了给 xpp 赋值呢？
                    return root;
                }
                /**
                 * 至此
                 * x 不为 null
                 * xp 不为 null
                 * xpp 不为 null
                 *
                 * x 为 red
                 * xp 为 red
                 */
                if (xp == (xppl = xpp.left)) {
                    if ((xppr = xpp.right) != null && xppr.red) {
                        xp.red = false;
                        xppr.red = false;
                        xpp.red = true;
                        x = xpp;
                        // 至此，本次循环代码已执行完毕
                    } else {
                        if (x == xp.right) {
                            // 现在 xp 位置左旋，就是旋转到下边了
                            root = rotateLeft(root, x = xp);
                            // 这里的 xp 如何又成了 null 呢？多线程
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateRight(root, xpp);
                            }
                        }
                    }
                } else {
                    if (xppl != null && xppl.red) {
                        xp.red = false;
                        xppl.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.left) {
                            root = rotateRight(root, x = xp);
                            xpp = (xp = x.parent) == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = rotateLeft(root, xpp);
                            }
                        }
                    }
                }
            }
        }

    }
}
