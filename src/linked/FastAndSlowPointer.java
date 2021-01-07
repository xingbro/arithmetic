package linked;

/**
 * 快慢指针
 *
 * @Author lx
 * @Date 2021/1/4 16:45
 * @Version 1.0
 */
public class FastAndSlowPointer {

    /**
     * 数组中包含 n + 1 个整数
     * 整数范围 [1,n]
     * 只有一个数字重复出现
     * 找到这个数
     *
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        /**
         * 快慢指针，有环必相遇
         * 1.环的入口
         * 2.相遇点
         *
         */
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
