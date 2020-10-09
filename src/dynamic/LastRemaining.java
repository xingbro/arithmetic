package dynamic;

import java.util.Random;

/**
 * 圆圈中最后剩下的数字
 *
 * @Author lx
 * @Date 2020/9/30 16:45
 * @Version 1.0
 */
public class LastRemaining {

    public static void main(String[] args) {
        int n = (int) (Math.random() * 100000000);
        int m = (int) (Math.random() * 100000000);
        lastIndex(n, m);
    }

    /**
     * n 个数排成一个环
     * 每次数到第 m 的人出列
     * 最后剩下的人原来排在第几号
     * @param n
     * @param m
     * @return
     */
    public static int lastIndex(int n, int m) {
        int pos = 0;
        for (int i = 2; i < n; i++) {
            pos = (pos + m) % i;
        }
        return pos;
    }
}
