package sort;

import java.util.Arrays;

/**
 * 堆排序
 * 在数组的基础上构建一个，最大（小）堆
 * 取出堆顶放置已排序的边界
 *
 * @Author lx
 * @Date 2020/9/26 10:48
 * @Version 1.0
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.getArray(10);
        System.out.println("排序前：" + Arrays.toString(array));
        sort1(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int len = arr.length;
        for (int i = len >> 1; i >= 0; i--) {
            heapify(arr, i, len);
        }
        for (int i = len - 1; i > 0; i--) {
            ArrayUtils.arraySwap(arr, 0, i);
            // --len 一直与 i 相等
            heapify(arr, 0, --len);
        }
    }

    public static void heapify(int[] arr, int i, int len) {
        // 未考虑 i * 2 之后越界
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


    public static void sort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = (arr.length >> 1) - 1; i >= 0; --i) {
            heapify1(arr, i, arr.length);
        }
        for (int j = arr.length - 1; j > 0; --j) {
            ArrayUtils.arraySwap(arr, 0, j);
            heapify1(arr, 0, j);
        }
    }

    public static void heapify1(int[] arr, int i, int len) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
