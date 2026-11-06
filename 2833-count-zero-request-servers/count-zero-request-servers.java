
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        int m = logs.length;
        int q = queries.length;

        int[] ans = new int[q];
        int[] count = new int[n + 1];

        Integer[] idx = new Integer[q];

        for (int i = 0; i < q; i++) {
            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) ->
            Integer.compare(queries[a], queries[b]));

        Arrays.sort(logs, (a, b) ->
            Integer.compare(a[1], b[1]));

        int left = 0;
        int right = 0;
        int active = 0;

        for (int i = 0; i < q; i++) {

            int time = queries[idx[i]];

            // Add logs inside the right boundary
            while (right < m && logs[right][1] <= time) {

                int id = logs[right][0];

                count[id]++;

                if (count[id] == 1) {
                    active++;
                }

                right++;
            }

            // Remove logs outside the left boundary
            while (left < right &&
                   logs[left][1] < time - x) {

                int id = logs[left][0];

                count[id]--;

                if (count[id] == 0) {
                    active--;
                }

                left++;
            }

            ans[idx[i]] = n - active;
        }

        return ans;
    }
}
