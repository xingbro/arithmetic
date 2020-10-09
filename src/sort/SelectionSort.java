package sort;

import java.util.Arrays;

/**
 * 选择排序
 * 每次从未排序的数据中，选出最小（大）的，放在已经排好序的末尾
 * @Author lx
 * @Date 2020/9/26 9:51
 * @Version 1.0
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.getArray(10);
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {return;}
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                ArrayUtils.arraySwap(arr, i, minIndex);
            }
        }
    }
}
