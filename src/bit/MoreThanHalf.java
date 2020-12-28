package bit;

/**
 * @Author lx
 * @Date 2020/12/28 11:23
 * @Version 1.0
 */
public class MoreThanHalf {

    /**
     * 数组中有元素出现次数超过了半数，找到这样的元素
     *
     * @param nums
     * @return
     */
    public static int majorityElement1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
             return nums[0];
        }
        int count = 0, current = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == current) {
                count++;
            } else if (--count < 1) {
                current = nums[i];
            }
        }
        return current;
    }

    public static int majorityElement2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0, current = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                current = num;
            }
            votes += num == current ? 1 : -1;
        }
        for (int num : nums) {
            if (num == current) {
                ++count;
            }
        }
        return count > nums.length / 2 ? current : 0;
    }
}
