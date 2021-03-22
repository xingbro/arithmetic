package bit;

/**
 * @Author lx
 * @Date 2020/12/8 16:44
 * @Version 1.0
 */
public class OnceNumber {

    /**
     * 整数数组，其中有一个数仅出现一次，其他数字均出现两次，
     * 找到出现一次这个数字
     *
     * @param nums
     * @return
     */
    public static int singleNumber1(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    /**
     * 整数数组，有一个数出现一次，其他数出现三次，
     * 找到出现一次这个数
     *
     * @param nums
     * @return
     */
    public static int singleNumber2(int[] nums) {
        /**
         * 这个单看某一位
         * 比如 0000 0010
         *
         *      0000 0010
         *      0000 0000
         *      two one 01
         *
         *      0000 0000
         *      0000 0010
         *      two one 10
         *
         *      0000 0000
         *      0000 0000
         *      two one 00
         *
         * 重要的是不同状态之间的转换
         */
        int one = 0, two = 0;
        for (int num : nums) {
            one = ~two & (one ^ num);
            two = ~one & (two ^ num);
        }
        return one;
    }

    /**
     * 整数数组，其中有两个数只出现一次，其他的都出现两次，
     * 找到出现一次这两个数
     *
     * @param nums
     * @return
     */
    public static int[] single2Numbers(int[] nums) {
        /**
         * [1] 通过 XOR 运算得到只出现一次的两个数 XOR 结果为：结果1
         * [2] 根据结果1，知道了这两个数的不同（结果1的二进制表示中，最后一位 1 的位置，一个是 1 ，一个是 0）
         * [3] 据此不同可将原来数组分成两组，每一组有一个单独的数，和若干成对出现的数
         */
        int twoNumbersXor = 0;
        for (int num : nums) {
            twoNumbersXor ^= num;
        }

        int diffBit = -twoNumbersXor & twoNumbersXor;

        int x = 0;
        for (int num : nums) {
            if ((num & diffBit) == 0) {
                x ^= num;
            }
        }
        return new int[]{x, x ^ twoNumbersXor};
    }
}
