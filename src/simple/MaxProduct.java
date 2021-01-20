package simple;

/**
 * 主要记录一下所犯的低级错误，避免这种极其弱智的做法
 * 1.考虑问题过于片面
 * 2.没有真正的去实施特定的方法，二是单纯的靠想
 * 3.比较的思维，找较大的几个，就从大往小找，找到合适位置，后边集体往后移动一位，较小的同理
 *
 *
 * @Author lx
 * @Date 2021/1/20 9:48
 * @Version 1.0
 */
public class MaxProduct {

    /**
     * 数组中找三个数相乘，找到最大乘积
     * 任意三个数乘积不超过 32 表示范围
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        // 可以说是简单到下限了
        // [1] 问题 1，只考虑最大三个数相乘，没考虑两个负数绝对值大的相乘再配一个最大正数也可能
        // [2] 问题 2，一遍扫描找出最大三个数，没弄清楚咋找。
        // 先想着后来的数，大于三个中的最小值，就可以入局，然后再依次判断和第二大的以及第一大的
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }

            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3){
                max3 = num;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }
}
