package bit;

/**
 * @Author lx
 * @Date 2020/12/22 15:05
 * @Version 1.0
 */
public class Addition {

    /**
     * 获取两整数的和
     * 不使用 + - 符号
     *
     * @param a
     * @param b
     * @return
     */
    public static int getSum(int a, int b) {
        /**
         * 代码解释方式一：
         * 1.a ^ b 包含了两数不同的部分（有 1 的算）
         * 2.a & b 包含了两数相同的部分，补充 1 中没有加上的，但是两数都有要 * 2 也就是 << 1
         *
         * 3.1 和 2 的结果要相加，也就是重复操作，出现循环体
         * 4.当没有相同部分时候，就不用加了，也就是边界
         *
         * 代码解释方式二：
         * 1.参考半加器和全加器，^ 运算来求和，& 运算来求进位
         * 2.进位一次结果可能还需要进位
         * 3.直到没有需要进位结束
         */
        while (b != 0) {
            int xor = a ^ b;
            b = (a & b) << 1;
            a = xor;
        }
        return a;
    }
}
