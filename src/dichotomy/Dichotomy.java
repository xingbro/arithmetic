package dichotomy;

/**
 * 二分法
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
}
