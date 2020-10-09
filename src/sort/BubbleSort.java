package sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 相邻的比较，较大（小）的换到相对后边的位置
 * @Author lx
 * @Date 2020/9/26 8:52
 * @Version 1.0
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.getArray(10);
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {return;}
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.arraySwap(arr, j, j + 1);
                }
            }
        }
    }
}
