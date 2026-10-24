
class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int a = days(arriveAlice), b = days(leaveAlice);
        int c = days(arriveBob), d = days(leaveBob);
        return Math.max(0, Math.min(b, d) - Math.max(a, c) + 1);
    }

    int days(String s) {
        int[] m = {31,28,31,30,31,30,31,31,30,31,30,31};
        int sum = 0, month = Integer.parseInt(s.substring(0, 2));
        for (int i = 0; i < month - 1; i++) sum += m[i];
        return sum + Integer.parseInt(s.substring(3));
    }
}
