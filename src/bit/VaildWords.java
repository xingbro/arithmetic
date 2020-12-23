package bit;

import java.util.HashMap;

/**
 * 1 << a (a 为小于 32 的整数) 来存储一个 a 相关的遍历，此例中为小写字母；
 * 又已知：整数 k 中包含 1 的情况，不断 k &= k - 1，可以数出 1 的个数；
 * 扩展到：含有 1 情况的子集，迭代值 j ,变化量 j - 1 再 & 上原来的初始全集值 k （不能含有 k 之外的多余 1）。
 *
 * 使用一个整数来存储，一个数组的东西（数组长度小于 32 ，每个值均不相同）
 * 遍历这个数组
 *
 * @Author lx
 * @Date 2020/12/23 14:29
 * @Version 1.0
 */
public class VaildWords {


    /**
     * puzzles 中含有长度为 7 的单词若干，
     * 每个 puzzle 中的字母都是小写，
     *
     * words 中含有长度为 4 ~ 50 的字符串若干，
     * 每个 word 中的字母也都是小写
     *
     * 如果 word 中含有 puzzle 的首字母，并且 word 中所有出现过的字母都在 puzzle 中找到，
     * 那么称 word 是 puzzle 的一个谜底
     *
     * 想要找到给定 puzzles 的每一个 puzzle 的谜底个数，在给定的 words 中
     *
     * @param words
     * @param puzzles
     * @return
     */
    public static int[] findNumOfValidWords(String[] words, String[] puzzles) {
        int[] nums = new int[puzzles.length];
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; ++i) {
            int k = 0;
            for (int j = 0; j < words[i].length(); ++j) {
                k |= 1 << words[i].charAt(j) - 'a';
            }
            map.put(k, map.get(k) == null ? 1 : map.get(k) + 1);
        }

        for (int i = 0; i < puzzles.length; ++i) {
            int k = 0;
            for (int j = 0; j < puzzles[i].length(); ++j) {
                k |= 1 << puzzles[i].charAt(j) - 'a';
            }
            for (int j = k; j > 0; j = k & j - 1) {
                if ((1 << puzzles[i].charAt(0) - 'a' & j) != 0) {
                    nums[i] += map.get(j) == null ? 0 : map.get(j);
                }
            }
        }

        return nums;
    }
}
