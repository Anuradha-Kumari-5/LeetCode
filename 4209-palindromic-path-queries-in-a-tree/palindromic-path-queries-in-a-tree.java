
import java.util.*;

class Solution {
    int[][] up;
    int[] depth, tin, tout, mask;
    int LOG, timer;
    List<Integer>[] graph;
    BIT bit;

    class BIT {
        int[] tree;
        int n;

        BIT(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= val;
            }
        }

        int query(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i) {
                res ^= tree[i];
            }
            return res;
        }

        void rangeUpdate(int l, int r, int val) {
            update(l, val);
            update(r + 1, val);
        }
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        mask = new int[n];

        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            mask[i] = 1 << (chars[i] - 'a');
        }

        // Iterative DFS to avoid stack overflow.
        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;
        up[0][0] = 0;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = ++count;
            order[count - 1] = u;

            for (int v : graph[u]) {
                if (v == up[0][u] && u != 0) continue;
                if (v == 0 && u == 0) continue;

                up[0][v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Build binary lifting table.
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[j][i] = up[j - 1][up[j - 1][i]];
            }
        }

        // Calculate subtree sizes and Euler exit times.
        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            if (u != 0) {
                size[up[0][u]] += size[u];
            }
        }

        for (int i = 0; i < n; i++) {
            tout[i] = tin[i] + size[i] - 1;
        }

        // Root-to-node character masks.
        bit = new BIT(n + 1);

        for (int u = 0; u < n; u++) {
            bit.rangeUpdate(tin[u], tout[u], mask[u]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                int newMask = 1 << (parts[2].charAt(0) - 'a');
                int diff = mask[u] ^ newMask;

                if (diff != 0) {
                    bit.rangeUpdate(tin[u], tout[u], diff);
                    mask[u] = newMask;
                }
            } else {
                int v = Integer.parseInt(parts[2]);
                int ancestor = lca(u, v);

                int pathMask = bit.query(tin[u])
                            ^ bit.query(tin[v])
                            ^ mask[ancestor];

                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}
