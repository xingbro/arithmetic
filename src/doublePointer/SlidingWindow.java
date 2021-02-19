package doublePointer;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 数组 nums 中元素范围[1, n]，其中 n 为数组长度
     * 有的元素只出现一次，有的出现两次
     * 所以[1, n]还有一些没出现的，找到这些没出现的
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        /**
         * [1] 模的一个应用，通过元素 + 模长的值，来标记是否被处理过
         * [2] 使用这个元素时候先对模长取模
         * [3] 使用这种方法目的在于，节约空间，但是改变了原来的数组
         *
         * 应用到本题
         * 有一点转换，就是下标和数值，遍历时候一定是看数值
         * 但是改变的是，下标为数值对应的值，查看时候看问题数值对应的下标
         */
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return list;
        }
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            nums[(nums[i] - 1) % len] += len;
        }
        for (int i = 0; i < len; ++i) {
            if (nums[i] <= len) {
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * 数组 nums 中只有 0 或者 1
     * 想要把数组中的元素全变成 1
     * 通过翻转（每次翻转是将，长度为 k 的子数组中，0 和 1 互换）
     *
     * @param nums
     * @param k
     * @return
     */
    public int minkBitFlips1(int[] nums, int k) {
        /**
         * 此题元素只有 0 和 1，相互转换的方式有 ^= 1
         * 每次新元素进入窗口，它必然参加了前边的翻转（这里依据，每个窗口至多翻转一次，得到最小翻转次数）
         * 为了保证翻转次数准确，这里先处理出去的，出去一个它进来时候翻转了的，就要减少一次翻转
         * 所以这里定义变量时候，注意有1.标记元素是否进入窗口时候翻转过，2.统计目前翻转的次数（由于只有 0 和 1，只统计奇偶即可）
         *
         */
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int n = nums.length;
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            // [1] 先处理要滑出窗口的
            if (i >= k && nums[i - k] >= 2) {
                revCnt ^= 1;
                nums[i - k] -= 2;
            }
            // [2] 再处理滑入窗口的，因为只统计了翻转次数的奇偶
            if (nums[i] == revCnt) {
                // 这个元素需要翻转，但是没有足够的窗口了
                if (i + k > n) {
                    return -1;
                }
                ++ans;
                revCnt ^= 1;
                nums[i] += 2;
            }
        }
        return ans;
    }

    /**
     * 上述问题的解法的前一个版本，使用到了差分数组
     *
     * 前一个解法，只考虑翻转次数的奇偶，如果想具体统计翻转次数，要使用一个长度为 k 的数组来记录，
     * 每次翻转，数组内的全部元素 +1，使用差分数组，就可以避免将 k 个全加一遍
     *
     * 差分数组，用来记录「主数组」的某一项与前一项的差值，第一项记录的是与 0 的差值
     *
     * @param nums
     * @param k
     * @return
     */
    public int minkBitFlips2(int[] nums, int k) {
        int n = nums.length;
        // n 有使用价值？没有，避免迭代部分代码特殊处理；
        // 翻转次数的数组差值，因为是从左向右遍历，所以前边翻转了没进来的差值就小了 1
        int[] diff = new int[n + 1];
        int ans = 0, revCnt = 0;
        for (int i = 0; i < n; ++i) {
            // revCnt 记录了当前元素经过的翻转次数，这里只是加的相比前一个差了多少
            revCnt += diff[i];
            if (((nums[i] + revCnt) & 1) == 0) {
                if (i + k > n) {
                    return -1;
                }
                ++ans;
                ++revCnt;
                --diff[i + k];
            }
        }
        return ans;
    }

    /**
     * 数组中 k 个连续子数组的最大值
     *
     * 此题解运用的「知识」
     * 1.《怎样解题》中提到，有没有一个和目标问题差不多（子问题或相似熟悉或简化等）的问题
     * 2.O(N) 不是扫描一遍，O(N) 不是扫描一遍，O(N) 不是扫描一遍
     * 3.单向扫描解决一部分问题遗漏一部分问题，反向扫描解决和遗漏互补非问题
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // [1] 判空及特殊值判断
        if (nums == null || nums.length < 1) {
            return new int[0];
        }
        // [2] 定义变量
        int len = nums.length;
        int[] pref = new int[len];
        int[] suff = new int[len];
        int[] result = new int[len - k + 1];
        // [3] 正向扫描，同组中左边的元素中最大值
        for (int i = 0; i < len; ++i) {
            if (i % k == 0) {
                pref[i] = nums[i];
            } else {
                pref[i] = Math.max(pref[i - 1], nums[i]);
            }
        }
        // [4] 反向扫描，同组中右边元素的最大值
        suff[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; --i) {
            // 分段结束的标志是，下一个又能整除了
            if ((i + 1) % k == 0) {
                suff[i] = nums[i];
            } else {
                suff[i] = Math.max(suff[i + 1], nums[i]);
            }
        }
        // [5] 找到 [i, i + k - 1] 内的最大值
        for (int i = 0; i < len - k + 1; ++i) {
            result[i] = Math.max(suff[i], pref[i + k - 1]);
        }
        return result;
    }
}
