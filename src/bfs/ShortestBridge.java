package bfs;

import java.util.LinkedList;

/**
 * 两个岛屿之间最短的桥
 * https://leetcode.cn/problems/shortest-bridge/
 *
 * @author lx
 * @date 2023/2/20
 */
public class ShortestBridge {

    /**
     * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
     * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
     * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
     * 返回必须翻转的 0 的最小数目。
     *
     * @param grid
     * @return
     */
    public static int shortestBridge(int[][] grid) {
        // 1.题意，两组 1，中间由 0 隔开的，找到一个连接这两组 1 的最小距离
        // 2.相似，深度优先&广度优先算法
        // 3.实施，先找到第一个岛，第一个岛的所有最外层进栈，然后通过第一个岛向外进行广度优先搜索
        // 4.验证，岛中间没有空的情况 and 岛中有空的情况；俩岛之间距离只有一个 and 有多个
        // 5.复盘，
        // 判空
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        // 使用深度优先，找第一个岛，
        // 定义一个队列，这里存岛 1 的外层(把整个岛都拿进来，不是外层的直接就判断就好了)
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[][] verify = new boolean[grid.length][grid[0].length];
        searchIsland1 : for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {
                if (grid[i][j] == 0) {
                    continue;
                }
                dfs(grid, i, j, verify, queue);
                break searchIsland1;
            }
        }
        // 此时岛 1 已经全部放入 queue 中
        int step = 0;
        int size;
        while ((size = queue.size()) > 0) {
            for (int i = 0; i < size; ++i) {
                if (bfs(grid, verify, queue)) return step;
            }
            step++;
        }
        return step;
    }

    /**
     * 深度优先算法，将岛 1 的数据找全，放入队列中
     *
     * @param grid
     * @param x
     * @param y
     * @param verify
     * @param queue
     */
    public static void dfs(int[][] grid, int x, int y, boolean[][] verify, LinkedList<Integer> queue) {
        // 边界判断
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || verify[x][y] || grid[x][y] == 0) {
            return;
        }
        // 标记处理过
        verify[x][y] = true;
        // 当前为 1，加入岛中，继续向下一级看
        queue.add(x * grid.length + y);
        dfs(grid, x - 1, y, verify, queue);
        dfs(grid, x + 1, y, verify, queue);
        dfs(grid, x, y - 1, verify, queue);
        dfs(grid, x, y + 1, verify, queue);
    }

    /**
     * 广度优先算法，查看当前队列中的第一个元素，的下一级是否能触碰到下一个
     *
     * @param grid
     * @param verify
     * @param queue
     * @return
     */
    public static boolean bfs(int[][] grid, boolean[][] verify, LinkedList<Integer> queue) {
        // 当前的长宽定义出来，方便判断越界引用
        int x = grid.length, y = grid[0].length;
        // 队列就先进的先出
        Integer first = queue.pollFirst();
        // 定义出当前出栈元素所在的位置
        int i = first / x, j = first % x;
        // 向外找一层，上下左右
        // 向上，没有验证过的
        if (i > 0 && !verify[i - 1][j]) {
            if (grid[i - 1][j] == 1) {
                return true;
            } else {
                // 标记验证过
                verify[i - 1][j] = true;
                // 当前值入队
                queue.add(first - x);
            }
        }
        // 向下
        if (i < x - 1 && !verify[i + 1][j]) {
            if (grid[i + 1][j] == 1) {
                return true;
            } else {
                verify[i + 1][j] = true;
                queue.add(first + x);
            }
        }
        // 向左
        if (j > 0 && !verify[i][j - 1]) {
            if (grid[i][j - 1] == 1) {
                return true;
            } else {
                verify[i][j - 1] = true;
                queue.add(first - 1);
            }
        }
        // 向右
        if (j < y - 1 && !verify[i][j + 1]) {
            if (grid[i][j + 1] == 1) {
                return true;
            } else {
                verify[i][j + 1] = true;
                queue.add(first + 1);
            }
        }
        return false;
    }
}
