package sort;

import java.util.Arrays;

/**
 * 堆排序
 * 在数组的基础上构建一个，最大（小）堆
 * 取出堆顶放置已排序的边界
 * @Author lx
 * @Date 2020/9/26 10:48
 * @Version 1.0
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.getArray(10);
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {return;}
        int len = arr.length;
        for (int i = len >> 1; i >= 0; i--) {
            heapify(arr, i, len);
        }
        for (int i = len - 1; i > 0; i--) {
            ArrayUtils.arraySwap(arr, 0, i);
            heapify(arr, 0, --len);
        }
    }

    public static void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largeIndex = i;
        if (left < len && arr[left] > arr[largeIndex]) {
            largeIndex = left;
        }
        if (right < len && arr[right] > arr[largeIndex]) {
            largeIndex = right;
        }
        if (largeIndex != i) {
            ArrayUtils.arraySwap(arr, i, largeIndex);
            heapify(arr, largeIndex, len);
        }
    }
}
