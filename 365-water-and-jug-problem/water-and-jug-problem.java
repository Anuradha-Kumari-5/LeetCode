class Solution {
    public boolean canMeasureWater(int x, int y, int targetCapacity) {

        if (targetCapacity > x + y) {
            return false;
        }

        return targetCapacity % gcd(x, y) == 0;
    }

    public int gcd(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}