package dynamic;

/**
 * 字符串匹配
 *
 * @Author lx
 * @Date 2021/3/3 10:20
 * @Version 1.0
 */
public class Match {

    /**
     * 看看 s 是不是模板 p 的一种
     * p 中的 "." 可以代表任意一个字符，p 中的 "*" 表示它前边的那个字符可以出现 0 到多次
     * 只考虑字符为小写字母情况
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        // 使用一个 dp[i][j] 来表示，s 中的第 i 个字符与 p 中的第 j 个是否匹配
        // 为什么会选取这个呢，因为 p 中的字符不能与 s 中的字符一一对应，而且不能一遍就排除前边已经匹配过的行还是不行，需要动态的来考虑
        // 怎么能想到这个点呢？或者说想不到是因为哪里限制了呢？
        // 还是先看自己怎么考虑的，再和这个思路对比一下差在哪里了，这底层的思考逻辑是什么？

        /**
         * 两个串，还想看是否能匹配上，直观上就是一个一个的对比，
         * 然而这里涉及到一个问题就是，当遇到 "*" 的时候，要回过头来重新看已经算是匹配好的情况，这就要有另一个分支，
         * 太麻烦，所以放弃了。
         * 遇到 "*" 就要重新考虑前边的那个字符，那么一定会反过来考虑，
         * 从后向前，先遇到这个符号，那看它前边的能不能匹配上及匹配上几个，这也要回过来看
         * 比较难处理的问题是 ".*" 子串
         *
         *
         * 以上要考虑的问题便是 m * n 种情况
         *
         * 分析这个问题，一定要看涉及到实际要解决的不确定性（原子问题）有多少？这些不确定性构成什么样的数据模型？
         * 综上：两个关键步骤 1.统计问题的不确定性，换句话说就是看清这个问题的本质，2.给这个整体找一个合适的数据模型
         *
         */

        // 硬匹配，存在问题
        /*int m = s.length() - 1, n = p.length();
        int mbMoreP = n, mbSnum = 0;
        for (int i = n - 1; i >= 0 && m >= 0; --i) {
            // 从后向前遍历模板
            if (p.charAt(i) == '*') {
                // 如果是个 个数通配符 那就要看它的前一个
                char cur = s.charAt(m);
                int numOfP = 0;
                // 如果 p.charAt(i) 是一个点，那它该不该匹配，不应该，因为这里是看模板是不是比目标多相同的字符，'.' 留着匹配下一个
                // 当然，如果这个是点有可能是多余的，只能用来匹配这一堆重复的字母，所以需要记录一下
                while (p.charAt(--i) == cur) {
                    mbMoreP = i;
                    ++numOfP;
                }
                // 因为 for 循环要迭代一次，所以这里先加回来
                ++i;
                // 第一个肯定等于
                int numOfS = -1;
                while (s.charAt(m) == cur) {
                    --m;
                    ++numOfS;
                }
                mbSnum = numOfS;
                if (numOfP > numOfS + 1) {
                    return false;
                }
            } else if (p.charAt(i) == '.') {
                // 是个点，得判断这个点是不是连续一堆之后的点，如果是有可能多余
                if (i == mbMoreP) {
                    // 往前看看是不是多余了这个点，这个也得循环着看
                    // 如果前边还是点，那么对于这个点的数量，对比一下重复字符的数量，点的数量多出来的是必须匹配新的，
                    // 要是往前又看到一个 '*' 可 tm 咋办？那咋办，目前从 * 过来的都是待定的
                }
                --m;
            } else if (p.charAt(i) != s.charAt(m)) {
                return false;
            } else {
                // 这里就是 p.charAt(i) == s.charAt(m) 情况
                --m;
            }
        }
        return true;*/

        // 使用动态规划，考虑状态值 dp[i][j] 表示 s 中的第 i 个字符和 p 中的第 j 个是否匹配
        if (s == null || p == null) {
            return true;
        }
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 分为第 j 个是不是 '*'
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 查看 s 中的第 i(下标对应 -1) 个字符与 p 中的第 j 个字符是否相同
     *
     * @param s
     * @param p
     * @param i
     * @param j
     * @return
     */
    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
