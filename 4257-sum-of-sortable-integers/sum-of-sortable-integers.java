
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] pref = new int[n + 1];
        int[] suff = new int[n + 1];
        int[] drop = new int[n];

        for (int i = 0; i < n; i++)
            pref[i + 1] = Math.max(pref[i], nums[i]);

        suff[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--)
            suff[i] = Math.min(nums[i], suff[i + 1]);

        for (int i = 1; i < n; i++)
            drop[i] = drop[i - 1] + (nums[i - 1] > nums[i] ? 1 : 0);

        int ans = 0;
        for (int k = 1; k <= n / k; k++) {
            if (n % k != 0) continue;
            if (check(nums, pref, suff, drop, k)) ans += k;
            int d = n / k;
            if (d != k && check(nums, pref, suff, drop, d)) ans += d;
        }
        return ans;
    }

    private boolean check(int[] a, int[] p, int[] s, int[] d, int k) {
        int n = a.length;
        for (int i = 0; i < n; i += k) {
            int j = i + k - 1;
            int drops = d[j] - d[i] + (a[j] > a[i] ? 1 : 0);
            if (drops > 1 || p[i] > s[i]) return false;
        }
        return true;
    }
}
