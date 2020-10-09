package sort;

/**
 * @Author lx
 * @Date 2020/9/26 9:44
 * @Version 1.0
 */
public class ArrayUtils {

    /**
     * 生成长度为 n 的数组
     * @param n
     * @return
     */
    public static int[] getArray(int n) {
        if (n < 0) {
            return null;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * n);
        }
        return arr;
    }

    /**
     * 交换数组对应下标元素
     * @param arr
     * @param i
     * @param j
     */
    public static void arraySwap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
