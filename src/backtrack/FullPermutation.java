package backtrack;

import sort.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 全排列
 * @Author lx
 * @Date 2020/9/27 10:30
 * @Version 1.0
 */
public class FullPermutation {

    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
        int[] nums = {1, 2, 3, 4, 5};
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }
        backtrack(5, output, res, 0);
        for (int i = 0; i < res.size(); i++) {
            for (int j = 0; j < res.get(i).size(); j++) {
                System.out.print(res.get(i).get(j) + " ,");
            }
            System.out.println();
        }
    }

    public static void backtrack(int n,
                                 List<Integer> output,
                                 List<List<Integer>> res,
                                 int first) {
        if (first == n) {
            res.add(new ArrayList<>(output));
        } else {
            for (int i = first; i < n; i++) {
                Collections.swap(output, i, first);
                backtrack(n, output, res, first + 1);
                Collections.swap(output, i, first);
            }
        }
    }
}
