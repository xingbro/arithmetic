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
        System.out.println("排序之前：" + Arrays.toString(array));
        double random = Math.random() * 3;
        if (random < 1) {
            sort(array, 0, array.length - 1);
            System.out.println("排序一后：" + Arrays.toString(array));
        } else if (random < 2) {
            int[] arrayCopy = mergeSort1(array, 0, array.length - 1);
            System.out.println("排序二后：" + Arrays.toString(arrayCopy));
        } else {
            int[] result = new int[array.length];
            mergeSort2(array, result, 0, array.length - 1);
            System.out.println("排序三后：" + Arrays.toString(array));
        }
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

    public static int[] mergeSort1(int[] arr, int p, int q) {
        if (p == q) {
            return new int[]{arr[p]};
        }
        int mid = (p & q) + ((p ^ q) >>1);
        int[] left = mergeSort1(arr, p, mid);
        int[] right = mergeSort1(arr, mid + 1, q);
        int[] newArr = new int[q - p + 1];
        int k = 0, i = 0, j = 0;
        while (i < left.length && j < right.length) {
            newArr[k++] = left[i] < right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) {
            newArr[k++] = left[i++];
        }
        while (j < right.length) {
            newArr[k++] = right[j++];
        }
        return newArr;
    }

    public static void mergeSort2(int[] arr, int[] result, int p, int q) {
        if (p >= q) {
            return;
        }
        int mid = (p & q) + ((p ^ q) >> 1);
        mergeSort2(arr, result, p, mid);
        mergeSort2(arr, result, mid + 1, q);
        int k = p, i = p, j = mid + 1;
        while (i <= mid && j <= q) {
            result[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            result[k++] = arr[i++];
        }
        while (j <= q) {
            result[k++] = arr[j++];
        }
        for (k = p; k <= q; k++) {
            arr[k] = result[k];
        }
    }
}
