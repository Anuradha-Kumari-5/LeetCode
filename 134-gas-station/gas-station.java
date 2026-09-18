class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalCost = 0;
        int totalGas = 0;

        // for(int val : gas){
        //     totalGas+= val;
        // }
        // for(int val : cost){
        //     totalCost+=val;
        // }
        // if(totalGas<totalCost){
        //     return -1;
        // }
        int start = 0;
        int currGas = 0;

        for(int i = 0; i<gas.length; i++){
            totalGas+= gas[i];
            totalCost+= cost[i];
            currGas = currGas + gas[i] - cost[i];
            if(currGas<0){
                start = i+1;
                currGas = 0;

            }

        }
        return totalGas<totalCost ? -1 : start;
    }
}