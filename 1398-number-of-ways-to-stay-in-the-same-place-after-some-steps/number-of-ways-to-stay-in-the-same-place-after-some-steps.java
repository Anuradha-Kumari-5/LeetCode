class Solution {
    public int numWays(int steps, int arrLen) {

        int MOD = 1000000007;

        int maxPos = Math.min(steps, arrLen - 1);

        int[] dp = new int[maxPos + 1];

        dp[0] = 1;

        for (int step = 1; step <= steps; step++) {

            int[] newDp = new int[maxPos + 1];

            for (int i = 0; i <= maxPos; i++) {

                // Stay
                newDp[i] = dp[i];

                // Come from left
                if (i > 0) {
                    newDp[i] = (newDp[i] + dp[i - 1]) % MOD;
                }

                // Come from right
                if (i < maxPos) {
                    newDp[i] = (newDp[i] + dp[i + 1]) % MOD;
                }
            }

            dp = newDp;
        }

        return dp[0];
    }
}