package sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 插入排序改进版，基本思想是，插入时候尽可能的少移动元素
 * 有一个按固定间距分组的概念
 * @Author lx
 * @Date 2020/9/26 11:12
 * @Version 1.0
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.getArray(10);
        System.out.println("排序前：" + Arrays.toString(array));
        double random = Math.random() * 1;
        if (random < 0.5) {
            sort1(array);
        } else {
            sort2(array);
        }
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void sort1(int[] arr) {
        if (arr == null || arr.length < 2) {return;}
        System.out.println("=====使用 sort1 排序=====");
        int gap = arr.length >> 1;
        while (gap > 0) {
            for (int i = 0; i < gap; i++) {
                for (int j = i + gap; j < arr.length; j += gap) {
                    int temp = arr[j];
                    int k = j - gap;
                    while (k >= 0 && arr[k] > temp) {
                        arr[k + gap] = arr[k];
                        k -= gap;
                    }
                    arr[k + gap] = temp;
                }
            }
            gap /= 2;
        }
    }

    public static void sort2(int[] arr) {
        if (arr == null || arr.length < 2) {return;}
        System.out.println("=====使用 sort2 排序=====");
        int j;
        for (int gap = arr.length >> 1; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }
}
