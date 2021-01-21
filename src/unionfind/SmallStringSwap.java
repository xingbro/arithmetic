package unionfind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 给一个字符串，和一个存储字符串中字符下标的二维数组，
 * 外层数组的元素是，可以交换的两个下标组成的数组
 * 只要是这两个下标可以换位，换多少次都行
 * 找到最后以字母表顺序为标准的最小顺序结果；
 * String s = "bcad";
 * nums = [[0, 3], [1, 2]];
 * String result = "bacd";
 *
 * @Author lx
 * @Date 2021/1/21 13:09
 * @Version 1.0
 */
public class SmallStringSwap {

    /**
     * 1.这种存在 彼此互换（前提：1 可以与 2 换，2 可以与3 换，结论：1 也可以与 3 换），就是相互连通
     * 2.把这些可以相互连通的构成一个乌托邦，也就是一个连通量，选出一个连通量代表元
     * 3.以代表元为 key，这个乌托邦中的所有元素集合为 value，再进行相应的操作
     *
     * @param s
     * @param pairs
     * @return
     */
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || "".equals(s) || pairs == null) {
            return s;
        }
        int len = s.length();
        // [1]初始化和分帮，别管能分成多少个乌托邦，总数跑不了，就是这些
        UnionFind uf = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        // [2]看看所有元素都是在哪个帮的，同一个帮内的，找到对应字符排个序
        HashMap<Integer, List<Character>> idxChMap = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            int root = uf.find(i);
            List<Character> list;
            if (idxChMap.containsKey(root)) {
                list = idxChMap.get(root);
            } else {
                list = new ArrayList<>();
            }
            setCharacter(list, s.charAt(i));
            idxChMap.put(root, list);
        }
        // [3]处理结果返回
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            sb.append(getCharacter(idxChMap.get(uf.find(i))));
        }
        return sb.toString();
    }

    /**
     * 将字符加入集合中，按照字母表顺序逆序（避免取出时候移动位置）
     *
     * @param list
     * @param c
     */
    private void setCharacter(List<Character> list, char c) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = (low & high) + ((low ^ high) >> 1);
            if (list.get(mid) > c) {
                // 比中间值小，得往后放
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (low == list.size()) {
            list.add(c);
        } else {
            list.add(low, c);
        }
    }

    /**
     * 获取集合中最后一个字符并且删除它
     *
     * @param list
     * @return
     */
    private Character getCharacter(List<Character> list) {
        if (list == null || list.size() < 1) {
            return null;
        }
        return list.remove(list.size() - 1);
    }
}
