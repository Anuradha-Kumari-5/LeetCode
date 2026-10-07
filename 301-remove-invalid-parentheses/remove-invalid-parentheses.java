import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        Set<String> set = new HashSet<>();

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum removals needed
        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRemove++;
            }
            else if (c == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                }
                else {
                    rightRemove++;
                }
            }
        }

        backtrack(s, 0, leftRemove, rightRemove,
                  0, 0, "", set);

        return new ArrayList<>(set);
    }

    private void backtrack(String s, int index,
                            int leftRemove, int rightRemove,
                            int leftCount, int rightCount,
                            String current,
                            Set<String> set) {

        // Reached end
        if (index == s.length()) {

            if (leftRemove == 0 &&
                rightRemove == 0 &&
                leftCount == rightCount) {

                set.add(current);
            }

            return;
        }

        char c = s.charAt(index);

        // Remove current character
        if (c == '(' && leftRemove > 0) {

            backtrack(
                s, index + 1,
                leftRemove - 1, rightRemove,
                leftCount, rightCount,
                current, set
            );
        }

        if (c == ')' && rightRemove > 0) {

            backtrack(
                s, index + 1,
                leftRemove, rightRemove - 1,
                leftCount, rightCount,
                current, set
            );
        }

        // Keep current character

        if (c != '(' && c != ')') {

            backtrack(
                s, index + 1,
                leftRemove, rightRemove,
                leftCount, rightCount,
                current + c, set
            );
        }

        else if (c == '(') {

            backtrack(
                s, index + 1,
                leftRemove, rightRemove,
                leftCount + 1, rightCount,
                current + c, set
            );
        }

        else if (c == ')' && rightCount < leftCount) {

            backtrack(
                s, index + 1,
                leftRemove, rightRemove,
                leftCount, rightCount + 1,
                current + c, set
            );
        }
    }
}