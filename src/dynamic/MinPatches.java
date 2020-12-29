package dynamic;

/**
 * @Author lx
 * @Date 2020/12/29 13:18
 * @Version 1.0
 */
public class MinPatches {

    /**
     * 想要使用 有序 数组 nums 中的任意个数字加和，来表示[1,n]中任意一个数
     * 看看数组 nums 还缺几个？
     *
     * 思路：
     * 依据 HashMap 中的规整 map 大小的办法，也就是一段黄金切两刀表示 7 种不同数
     * (bit.PowerOf2)
     * 比如现在数组中的数，可以表示 x - 1,但是不能表示 x,
     * 那么此时，只需要加上一个 x,就可以表示到 2 * x - 1 了
     *
     * @param nums
     * @param n
     * @return
     */
    public static int minPatches(int[] nums, int n) {
        if (nums == null) {
            return 0;
        }
        int patches = 0, index = 0;
        // x 意思是 [1,x] 或 [1, x - 1] 区间都能表示
        long x = 1;
        while (x <= n) {
            if (index < nums.length && nums[index] <= x) {
                // x 在这里边加上的都是已有的数字，加的每一个都是实实在在可以表示的
                x += nums[index];
                index++;
            } else {
                // 此代码块执行之后，相当于在原来数组中加了一个执行前的 x，所以外层 while 要带等号
                x *= 2;
                patches++;
            }
        }
        return patches;
    }
}
