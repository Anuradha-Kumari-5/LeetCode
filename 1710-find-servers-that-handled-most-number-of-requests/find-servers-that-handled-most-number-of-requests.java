
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] count = new int[k];

        for (int i = 0; i < arrival.length; i++) {

            // Release servers whose requests are completed
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= arrival[i]) {

                int[] finished = busy.poll();
                available.add(finished[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            // Find the next available server
            int start = i % k;

            Integer server = available.ceiling(start);

            // Wrap around if necessary
            if (server == null) {
                server = available.first();
            }

            available.remove(server);

            count[server]++;

            // Store finishing time and server index
            busy.offer(new int[]{
                arrival[i] + load[i], server
            });
        }

        int max = 0;

        for (int c : count) {
            max = Math.max(max, c);
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == max) {
                ans.add(i);
            }
        }

        return ans;
    }
}
