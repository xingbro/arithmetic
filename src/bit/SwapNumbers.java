package bit;

import java.util.Arrays;

/**
 * 不使用临时变量交换数组中两元素的位置
 * @Author lx
 * @Date 2020/11/18 10:34
 * @Version 1.0
 */
public class SwapNumbers {

    public static void main(String[] args) {
        int[] nums = {5, 7};
        System.out.println("交换前：" + Arrays.toString(nums));
        swapNumbers(nums, 0, 1);
        System.out.println("交换后：" + Arrays.toString(nums));
    }

    /**
     * 异或( ^ )运算
     * ①一个数 ^ 本身为零，a ^ a = 0;
     * ②两个数 ^ 之后，结果中存储着两个数的信息，用其中一个 ^  结果，可以得到另一个数：
     *      c = a ^ b; 可得到：
     *      a == c ^ b;
     *      b == c ^ a;
     * ③由②可知两个变量可以存储三个数的信息
     * 注：如果两个下标相同，将会把此下标位置值变成 0
     *
     * @param nums
     * @param i
     * @param j
     * @return
     */
    public static int[] swapNumbers(int[] nums, int i, int j) {
        if (i == j) {
            return nums;
        }
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
        return nums;
    }
}
