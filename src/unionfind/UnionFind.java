package unionfind;

/**
 * 并查集模板
 *
 * @Author lx
 * @Date 2021/1/19 14:58
 * @Version 1.0
 */
public class UnionFind {

    private int[] leader;

    public UnionFind(int size) {
        this.leader = new int[size];
        for (int i = 0; i < size; ++i) {
            leader[i] = i;
        }
    }

    public void union(int index1, int index2) {
        leader[find(index2)] = find(index1);
    }

    public int find(int index) {
        if (leader[index] != index) {
            leader[index] = find(leader[index]);
        }
        return leader[index];
    }
}
