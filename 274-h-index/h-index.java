class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int Hmax = 0;
        Arrays.sort(citations);
        for (int i = 0 ; i<n ; i++){
            int h = n - i;
            if (citations[i] >= h){
               Hmax = Math.max(Hmax, h);
            }
        }
        return Hmax;
    }
}