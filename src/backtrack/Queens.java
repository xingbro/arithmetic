package backtrack;

import java.util.*;

/**
 * N 皇后所有可能情况
 * @Author lx
 * @Date 2020/9/27 9:28
 * @Version 1.0
 */
public class Queens {

    public static void main(String[] args) {
        int n = (int) (Math.random() * 9);
        if (n < 1) {
            System.out.println("=====没有皇后要排列=====");
        } else {
            List<List<String>> res = new ArrayList<>();
            int[] queens = new int[n + 1];
            Set<Integer> columns = new HashSet<>();
            Set<Integer> diagonalDown = new HashSet<>();
            Set<Integer> diagonalUp = new HashSet<>();
            backtrack(res, queens, n + 1, 1, columns, diagonalDown, diagonalUp);
            // 打印结果
            System.out.println(n + " 皇后的排列结果有 " + res.size() + " 种");
            for (int i = 0; i < res.size(); i++) {
                List<String> list = res.get(i);
                System.out.println("第 " + (i + 1) + " 种排列结果为：");
                for (int j = 0; j < n; j++) {
                    System.out.println(list.get(j));
                }
            }
        }
    }

    /**
     * 回溯，尝试找到第 row 行，皇后所在合适的列
     * @param res 封装结果的容器
     * @param queens 下标行对应皇后所在列
     * @param n 皇后的个数（实际为皇后个数 +1，因为数组未使用数组下标为 0 的位置）
     * @param row 要确定 Queen 在哪一列的行数，根据数组 queens 含义理解
     * @param columns 哪一列已经有 Queen 了的集合
     * @param diagonalDown 哪一下斜已经有 Queen 了的集合
     * @param diagonalUp 哪一上斜已经有 Queen 了的集合
     */
    public static void backtrack(List<List<String>> res,
                             int[] queens,
                             int n,
                             int row,
                             Set<Integer> columns,
                             Set<Integer> diagonalDown,
                             Set<Integer> diagonalUp) {
        if (row == n) {
            res.add(new ArrayList<>(generateBoard(queens, n)));
        } else {
            for (int i = 1; i < n; i++) {
                /**
                 * 此循环是为了：给传进来的第 row 行，找到合适皇后位置在第 i 列
                 */
                // [1] 查看第 i 列是否已经有皇后占用
                if (columns.contains(i)) {
                    continue;
                }
                // [2] 查看（从左向右方向看）向下斜的对角线，是否有皇后占用
                int down = row - i;
                if (diagonalDown.contains(down)) {
                    continue;
                }
                // [3] 查看（从左向右方向看）向上斜的对角线，是否有皇后占用
                int up = row + i;
                if (diagonalUp.contains(up)) {
                    continue;
                }

                // 对这一行进行设值尝试
                queens[row] = i;
                columns.add(i);
                diagonalDown.add(down);
                diagonalUp.add(up);

                // 尝试下一行，看是否能继续到完成排列
                backtrack(res, queens, n, row + 1, columns, diagonalDown, diagonalUp);

                // 这一行之后的所有可能已经尝试完了，回退上一行
                queens[row] = 0;
                columns.remove(i);
                diagonalDown.remove(down);
                diagonalUp.remove(up);
            }
        }
    }

    /**
     * 将数组表示的对应行列转换成为要打印的结果
     * @param queens
     * @param n
     * @return
     */
    public static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 1; i < n; i++) {   // 第 i 行的放置皇后
            char[] c = new char[n - 1];
            Arrays.fill(c,'.');
            // 第 i 行直接存的是第几列应放置皇后，放入数组时候 -1 转换到下标
            c[queens[i] - 1] = 'Q';
            board.add(new String(c));
        }
        return board;
    }
}
