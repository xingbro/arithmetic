package dynamic;

/**
 * 视屏剪辑，是区间组合保证覆盖全区域的问题
 *
 * @Author lx
 * @Date 2021/3/4 14:40
 * @Version 1.0
 */
public class Clips {

    /**
     * clips 中是已有的片段，想要拼接成完整的从 0 到 t
     * 选出最少需要的片段数量
     *
     * 1 <= clips.length <= 100
     * 0 <= clips[i][0] <= clips[i][1] <= 100
     * 0 <= t <= 100
     *
     * @param clips
     * @param t
     * @return
     */
    public int videoStitching(int[][] clips, int t) {
        // 直观的按照人处理这题得方式模拟
        // [1] 判空及特殊值处理
        if (clips == null || clips.length < 1 || clips[0] == null || clips[0].length < 1) {
            return 0;
        }
        // [2] 挨个看一遍，看看从每一个起点最远能到哪，就使用最远那个
        int[] maxn = new int[t];
        for (int[] clip : clips) {
            if (clip[0] < t) {
                // 找到这个起始位置能到的最远值
                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
            }
        }
        // [3] 从 0 开始看每个位置能到达最远到哪，不断添加片段向后延
        int last = 0, pre = 0, res = 0;
        for (int i = 0; i < t; ++i) {
            // 看看最远能到哪
            last = Math.max(last, maxn[i]);
            // 如果最远只能到脚下，那没招了，只能返回
            if (last == i) {
                return -1;
            }
            // 如果现在这个片段不够往下维护了，那就加一个片段，选这个期间能到达的最远那个
            if (pre == i) {
                ++res;
                pre = last;
            }
        }
        return res;
    }

    /**
     * 上述问题动态规划版本
     *
     * @param clips
     * @param t
     * @return
     */
    public int videoStitchingOfdp(int[][] clips, int t) {
        /**
         * 1.要从想要解决的问题考虑迭代，此题是完成从 0 延续到 t，所以就得看 [0, t] 的连续关系
         * 2.状态值一定是，想要到达这个位置，使用的最小片段数
         * 3.状态转移，想要到达这个点，那么看看能跨过这个点起始点最小是多少，再加上 1 不就是能到现在了
         */
        // [1] 判空及特殊处理
        // [2] 定义 dp 数组，进行最小值填充
        int[] dp = new int[t + 1];
        for (int i = 1; i < t; ++i) {
            dp[i] = 101;
        }
        // [3] 输入区间内每个点需要的最小片段数
        for (int i = 1; i <= t; ++i) {
            for (int[] clip : clips) {
                // 区间包含当前点
                if (clip[0] < i && clip[1] >= i) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[t] == 101 ? -1 : dp[t];
    }
}
