class Solution {
    public boolean canJump(int[] nums) {
        int currentReach = 0;
        for(int i = 0; i<nums.length; i++){
            if(i>currentReach){
                return false;
            }
            currentReach = Math.max(currentReach, i + nums[i]);
            if(currentReach >= nums.length - 1){
                return true;
            }
            
        }
        return true;
    }
}