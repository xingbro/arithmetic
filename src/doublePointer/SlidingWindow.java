package doublePointer;

/**
 * 滑动窗口
 * 是一种典型的 线性时间 解决问题的思维模式
 * 学习初期对模型的的理解
 * 1.已有窗口左指针、右指针
 * 2.每次迭代的变量，右指针右移
 *      如果新加入的变量满足 可以在窗口中的条件 那么左指针不动，相当于窗口扩大 1
 *      新加入的变量不满足，维持原有窗口大小，左指针也右移
 * 3.最后窗口的大小，考虑为所需要的最大长度等结果
 *
 * @Author lx
 * @Date 2021/2/2 10:08
 * @Version 1.0
 */
public class SlidingWindow {

    /**
     * 字符串中都是大写字母，其中最多可以更换其中 k 个字母，
     * 更换后，使得 s 中存在连续的相同字母的个数最大
     *
     * 题解中：letterNums 中统计了 现在 窗口中，字母为下标值的个数；
     * max 是一个特殊的变量，它统计了窗口中，存在过的（无论哪个字母）长度最大值，
     * 同时它也窗口在移动时候，大小的唯一变量
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() < 1) {
            return 0;
        } else if (k > s.length() - 1) {
            return s.length();
        }

        int length = s.length();
        int left = 0, right = 0, max = 0;
        int[] letterNums = new int[26];

        while (right < length) {
            max = Math.max(max, ++letterNums[s.charAt(right) - 'A']);
            if (right - left + 1 - k > max) {
                --letterNums[s.charAt(left) - 'A'];
                ++left;
            }
            ++right;
        }

        return right - left;
    }

    /**
     * 正整数数组，元素大小不超过数组长度
     * 找到这样的子数组个数，子数组中不同元素恰好为 k 个
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraysWithKDistinct(int[] nums, int k) {
        /**
         * 1.分析恰好为 k 个，指针单向移动，不能完全数清楚
         * 2.做此题之前需要先了解，最多为 k 个的情况如何计算
         * 问题拆解为熟悉的问题 +1
         */
        if (nums == null || nums.length < 1) {
            return 0;
        }
        return maxNum(nums, k) - maxNum(nums, k - 1);
    }

    /**
     * 条件同上
     * 找到这样子数组的个数，子数组中不同元素个数最多为 k
     *
     * @param nums
     * @param k
     * @return
     */
    private int maxNum(int[] nums, int k) {
        /**
         * 1.使用数组计数（原数据大小范围有限，这个计数数组下标范围能很好的包含这个范围）
         * 2.每次移动指针对于结果的贡献要明确，所有指针移动的结果加一起，刚好能完成全部
         */
        int res = 0;
        int left = 0, right = 0, count = 0;
        int len = nums.length;
        int[] numCount = new int[len + 1];
        while (right < len) {
            if (numCount[nums[right++]]++ == 0) {
                ++count;
            }
            while (count > k) {
                if (--numCount[nums[left++]] == 0) {
                    --count;
                }
            }
            res += right - left;
        }
        return res;
    }
}
