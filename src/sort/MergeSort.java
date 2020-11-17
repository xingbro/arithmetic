package sort;

import java.util.Arrays;

/**
 * 归并排序
 * 先分解任务，直到分解为有序（1 个元素）
 * 再合并
 * @Author lx
 * @Date 2020/9/26 10:59
 * @Version 1.0
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = ArrayUtils.getArray(10);
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array, 0, array.length - 1);
        System.out.println("排序后：" + Arrays.toString(array));
    }

    public static void sort(int[] arr, int p, int q) {
        if (p < q) {
            int mid = (p & q) + ((p ^ q) >> 1);
            sort(arr, p, mid);
            sort(arr, mid + 1, q);
            merge(arr, p, mid, q);
        }
    }

    public static void merge(int[] arr, int p, int mid, int q) {
        int l = mid - p + 1;
        int r = q - mid;
        int[] left = new int[l + 1];
        int[] right = new int[r + 1];
        for (int i = 0; i < l; i++) {
            left[i] = arr[p + i];
        }
        for (int j = 0; j < r; j++) {
            right[j] = arr[mid + j + 1];
        }
        left[l] = right[r] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for (int k = p; k < q + 1; k++) {
            if (left[i] < right[j]) {
                arr[k] = left[i++];
            } else {
                arr[k] = right[j++];
            }
        }
    }
}
