class Solution {
    public int reverse(int x) {

        boolean neg = x < 0;

        String s = String.valueOf(Math.abs(x));

        StringBuilder sb = new StringBuilder(s);
        sb.reverse();

        try {
            int ans = Integer.parseInt(sb.toString());

            if (neg) {
                ans = -ans;
            }

            return ans;

        } catch (Exception e) {
            return 0;
        }
    }
}