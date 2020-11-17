package dynamic;

import sort.ArrayUtils;

/**
 * 最长增长子序列
 * @Author lx
 * @Date 2020/9/27 17:17
 * @Version 1.0
 */
public class Subsequence {

    public static void main(String[] args) {
        int[] nums = ArrayUtils.getArray(10);
        int n;
        for (int i = 0; i < 10; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
        if (Math.random() < 0.5) {
            n = legnthOfLIS1(nums);
            System.out.println("使用第 1 种（二分法）方式得到结果：" + n);
        } else {
            n = legnthOfLIS2(nums);
            System.out.println("使用第 2 种（全列出）方式得到结果：" + n);
        }
    }

    public static int legnthOfLIS1(int[] nums) {
        if (nums == null || nums.length < 1) {return 0;}
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int i = 0, j = size;
            while (i != j) {
                int mid = (i & j) + ((i ^ j) >> 1);
                if (tails[mid] < num) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            tails[i] = num;
            if (i == size) {
                ++size;
            }
        }
        return size;
    }

    public static int legnthOfLIS2(int[] nums) {
        if (nums == null || nums.length < 1) {return 0;}
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxValue = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            maxValue = Math.max(maxValue, dp[i]);
        }
        return maxValue;
    }

}
