package dichotomy;

/**
 * 二分法
 *
 * 部分对于位运算的使用
 * & 1 (和 1 进行与运算AND);奇数为 1，偶数为 0
 * ^ 1 (和 1 进行异或运算XOR);奇数 -1，偶数 +1
 * | 1 (和 1 进行或运算OR);奇数不变，偶数 +1
 * ~ 是单目运算符，按位取反，不讨论
 *
 *
 * @Author lx
 * @Date 2021/1/7 9:15
 * @Version 1.0
 */
public class Dichotomy {

    /**
     * 有序数组无重复数字
     * 找到目标值所在的下标，没有返回 -1
     * 说明：最基本的二分查找，边界使用、变量值
     *
     * @param nums
     * @param target
     * @return
     */
    public static int dichotomySearch(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        // 1.边界问题，使用数组长度为下标，会不会越界？
        // 不会。想要到达长度下标，必须保证 left 和 right 都是 length，但是这样的 while 条件过不了
        int left = 0, right = nums.length;
        // 2.这里的条件，如果 left >= right 了，说明没有找到合适的
        while (left < right) {
            int mid = (left & right) + ((left ^ right) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // 这里不能 -1，保证下标为 0 的元素可以访问
                right = mid;
            }
        }
        return -1;
    }

    /**
     * 有序的整数数组，每个数字都出现两次
     * 只有一个出现一次，找出这个数
     *
     * O(n) XOR 即可，但是没有使用到有序的条件
     * 因为也没有目标值，所以此处的有序条件，只是作为：相等两数相邻
     *
     * 考虑 1：中间两个值相等或者不相等，加上各自情况下左右分别是奇数还是偶数
     * 有两个变量，且在两数不相等的情况下，判断出在哪侧比较复杂
     *
     * 考虑 2：下标从 0 开始，成对出现的数，第一个是偶数下标，下一个是挨着它的奇数下标
     * 某个偶数下标的下一个不等于本身，说明包含在左侧区间内
     *
     * @param nums
     * @return
     */
    public static int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        /**
         * 数组长度为奇数个
         * 搜索偶数下标的值，和它后边的一个值是否相等
         * 相等，说明单个的数在右侧区间
         *
         * 边界问题，因为是找到特定元素的下标，且在循环中没有 return，
         * 所以 left < right 不带 = ，避免一直循环下去（left == right, right = mid）
         * mid ^ 1 保证了 mid +/- 1 与 mid 是偶数在前的相邻两数
         *
         */
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left & right) + ((left ^ right) >> 1);
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left];
    }
}
