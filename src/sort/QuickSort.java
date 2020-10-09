package sort;

import java.util.Arrays;

/**
 * 快速排序
 * 找一个锚定点，小的放左边，大的放右边，左右再同理
 * @Author lx
 * @Date 2020/9/26 11:08
 * @Version 1.0
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.getArray(10);
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array, 0, array.length - 1);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void sort(int[] arr, int p, int q) {
        if (p < q) {
            int i = p - 1;
            for (int j = p; j < q; j++) {
                if (arr[j] < arr[q]) {
                    ArrayUtils.arraySwap(arr, ++i, j);
                }
            }
            ArrayUtils.arraySwap(arr, i + 1, q);
            sort(arr, p, i);
            sort(arr, i + 2, q);
        }
    }
}
