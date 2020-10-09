package sort;

import java.util.Arrays;

/**
 * 插入排序
 * 从未排序的序列取出元素，插入已排序的合适位置
 * @Author lx
 * @Date 2020/9/26 8:54
 * @Version 1.0
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.getArray(10);
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {return;}
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    ArrayUtils.arraySwap(arr, i, j);
                }
            }
        }
    }
}
