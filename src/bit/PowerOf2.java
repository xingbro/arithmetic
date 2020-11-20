package bit;

import java.util.Random;

/**
 * @Author lx
 * @Date 2020/11/18 13:15
 * @Version 1.0
 */
public class PowerOf2 {

    public static void main(String[] args) {
        double random = Math.random() * 4;
        int num;
        switch ((int) random) {
            case 0:
                num = (int) (Math.random() * (Integer.MAX_VALUE >> 22));
                break;
            case 1:
                num = (int) (Math.random() * (Integer.MAX_VALUE >> 15));
                break;
            case 2:
                num = (int) (Math.random() * (Integer.MAX_VALUE >> 8));
                break;
            case 3:
                num = (int) (Math.random() * (Integer.MAX_VALUE >> 1));
                break;
            default:
                num = 0;
        }
        System.out.println("大于 " + num + " 的，2 的幂数中，最小一个是：" + getMin2PowerOverThisNum(num));
    }

    /**
     * 输入数值（非 2 的幂数）二进制数中最高位 1 开始，后边位先全变成 1 ，再 + 1；
     * 取自 HashMap(JDK1.8) 中，构造函数对于传入容器大小的调整
     * n |= n >>> 1; n 中最高位的 1 以及第 2 高位，都是 1 了，相当于把最高位 1 在后边复制一份
     * n |= n >>> 2; n 中最高 4 位都是 1 了，最高 2 位 1 向后复制一份
     * ......
     * n |= n >>> 16; 保证 n 中最多有 31 位表示数值部分都可以是 1
     * @param num
     * @return
     */
    public static int getMin2PowerOverThisNum(int num) {
        int n = num - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n < 0 ? 1 : n + 1;
    }
}
