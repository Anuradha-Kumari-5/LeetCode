class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 0 && dfs(grid, i, j))
                    count++;

        return count;
    }

    boolean dfs(int[][] g, int i, int j) {
        if (i < 0 || j < 0 || i >= g.length || j >= g[0].length)
            return false;
        if (g[i][j] == 1) return true;

        g[i][j] = 1;

        boolean a = dfs(g, i + 1, j);
        boolean b = dfs(g, i - 1, j);
        boolean c = dfs(g, i, j + 1);
        boolean d = dfs(g, i, j - 1);

        return a && b && c && d;
    }
}