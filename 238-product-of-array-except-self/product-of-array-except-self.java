class Solution {
    public int[] productExceptSelf(int[] nums) {
        
        int n = nums.length;
        int product[] = new int[n];
        
        product[0] = 1;
        
        // prefix

        for(int i = 1; i<n ; i++){
            product[i] = product[i-1]*nums[i-1];
        }

        // Suffix

        int suffix = 1;

        for(int i = n -2; i>=0; i--){
            suffix *=nums[i+1];
            product[i] = product[i]* suffix;
        }
        return product;
    }
}