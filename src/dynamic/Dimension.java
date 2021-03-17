package dynamic;

/**
 * 维度
 *
 * @Author lx
 * @Date 2021/3/17 10:33
 * @Version 1.0
 */
public class Dimension {

    /**
     * 字符串 s 是母串
     * s 中有多少个 t 序列（可以不连续的子串）
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        /**
         * 1.本质问题分析，很容易想到的是单层循环，扫描一遍 s ，看从匹配上第一个开始，后续怎么变化？
         * 显然已经匹配上一个这一段中，很可能存在重叠的字母，也就是可以用两次以上
         * 2.如果从一开始就想着，t 的前缀开始一遍一遍的在 s 中匹配，那这个问题还挺简单
         * 3.问题是，怎么能从一遍扫描思维，转换到这个二维的思考？真正了解问题的本质是最有效的途径
         *
         */
        // [1] 判空及特殊值处理
        // [2] 定义二维数组，将 t 的前缀子串，依次在 s 中进行匹配
        int m = t.length(), n = s.length();
        int[][] dp = new int[m][n];
        if (t.charAt(0) == s.charAt(0)) {
            dp[0][0] = 1;
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (s.charAt(j) == t.charAt(i)) {
                    dp[i][j] = dp[i][j - 1] + (i > 0 ? dp[i - 1][j - 1] : 1);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
