package unionfind;

/**
 * 并查集模板
 *
 * @Author lx
 * @Date 2021/1/19 14:58
 * @Version 1.0
 */
public class UnionFind {

    private int[] parent;

    private int[] rank;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; ++i) {
            parent[i] = i;
            rank[i] = i;
        }
    }

    public void union(int index1, int index2) {
        int root1 = find(index1);
        int root2 = find(index2);
        if (root1 == root2) {
            return;
        }
        if (rank[root1] == rank[root2]) {
            parent[root1] = root2;
            ++rank[root2];
        } else if (rank[root1] < rank[root2]) {
            parent[root1] = root2;
        } else {
            parent[root2] = root1;
        }
    }

    public int find(int index) {
        if (parent[index] != index) {
            parent[index] = find(parent[index]);
        }
        return parent[index];
    }
}
