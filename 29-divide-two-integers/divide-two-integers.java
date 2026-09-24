class Solution {
    public int divide(int dividend, int divisor) {

        // Overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Check whether answer should be negative
        boolean negative = (dividend < 0) != (divisor < 0);

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        long result = 0;

        while (a >= b) {

            long value = b;
            long count = 1;

            // Keep doubling
            while (a >= value + value) {
                value = value + value;
                count = count + count;
            }

            // Remove the biggest possible chunk
            a = a - value;

            // Add how many divisors were in that chunk
            result = result + count;
        }

        // Apply negative sign
        if (negative) {
            result = -result;
        }

        return (int) result;
    }
}