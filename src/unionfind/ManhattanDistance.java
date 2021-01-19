package unionfind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 坐标点，曼哈顿距离是 Math.abs(x1 - x2) + Math.abs(y1 - y2)
 * 找到所有点最小曼哈顿距离和
 *
 * @Author lx
 * @Date 2021/1/19 16:24
 * @Version 1.0
 */
public class ManhattanDistance {

    List<Integer> list;

    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length < 2) {
            return 0;
        }
        // 初始化，leader 选为自己
        list = new ArrayList<>();
        int length = points.length;
        for (int i = 0; i < length; ++i) {
            list.add(i);
        }
        // 曼哈顿距离，一定要从所有的距离中选出来，所以最开始要把两两距离都算出来
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i < length - 1; ++i) {
            for (int j = i + 1; j < length; ++j) {
                edges.add(new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }
        // 把这个距离排个序，距离小的往前排，然后从前边开始入伙，这样保证了这个点入伙的时候，是跟随最短的边
        Collections.sort(edges, (a, b) -> a.val - b.val);
        int res = 0;
        for (Edge edge : edges) {
            int a = find(edge.a), b = find(edge.b);
            if (a != b) {
                list.set(a, b);
                res += edge.val;
            }
        }
        return res;
    }

    public int find(int index) {
        if (list.get(index) != index) {
            list.set(index, find(list.get(index)));
        }
        return list.get(index);
    }
}

/**
 * 结构体，用来存储 两点和两点间距离的关系
 */
class Edge {

    int a, b, val;

    public Edge(int a, int b, int val) {
        this.a = a;
        this.b = b;
        this.val = val;
    }
}
