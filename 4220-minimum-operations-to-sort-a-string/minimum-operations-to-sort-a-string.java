
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Check if the string is already sorted
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;

        // Cannot sort a reversed string of length 2
        if (n == 2) return -1;

        char minChar = 'z';
        char maxChar = 'a';

        for (int i = 0; i < n; i++) {
            minChar = (char) Math.min(minChar, s.charAt(i));
            maxChar = (char) Math.max(maxChar, s.charAt(i));
        }

        // One operation is enough
        if (s.charAt(0) == minChar ||
            s.charAt(n - 1) == maxChar) {
            return 1;
        }

        // Check whether the minimum or maximum
        // character appears inside the string
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == minChar ||
                s.charAt(i) == maxChar) {
                return 2;
            }
        }

        return 3;
    }
}
