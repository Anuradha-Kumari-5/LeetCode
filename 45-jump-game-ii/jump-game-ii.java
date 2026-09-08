class Solution {
    public int jump(int[] nums) {
        int currentReach = 0;
        int count = 0;
        int farthestJump = 0;
        for(int i = 0; i<nums.length - 1; i++){
           
             farthestJump = Math.max( farthestJump, i + nums[i]);
               
            
           if(i == currentReach ){
           
            count++;
            currentReach =  farthestJump;
           }
          
        }
        return count;
    }
}