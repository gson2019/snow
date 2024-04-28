package com.company;

import java.util.ArrayList;
import java.util.List;

public class LC1570 {
    // If you get a follow-up that for non-sparse array how to optimize solution,
    // use binary search as the indexes of non-zero elements will be sorted
    List<int[]> numIndexList = new ArrayList<int[]>();

    //Time = O(N) Space O(Max(L1,L2)) L non-zero elements
    LC1570(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            //Capture all the non-zero elements in list of array of int {index, value}
            if (nums[i] != 0) numIndexList.add(new int[] {i, nums[i]});
        }
    }

    // Return the dotProduct of two sparse vectors
    //Time = O(L1.log(L2)) Space O(1) L non-zero
    public int dotProduct(LC1570 vec) {
        //calculate sum such that iteration happens over vector with lesser number of non-zero elements.

        if (this.numIndexList.size() < vec.numIndexList.size()){
            return dotProduct(this, vec);
        } else {
            return dotProduct(vec, this);
        }
    }

    public int dotProduct(LC1570 smallVec, LC1570 largeVec) {
        int result = 0;
        for (int[] curr: smallVec.numIndexList){
            // perform binary search to find the curr non-zero element index in larger non-zero element vector.
            int[] exists = binarySearch(largeVec.numIndexList, curr[0]);
            if (exists[0] == curr[0]) result += curr[1]*exists[1];
        }
        return result;
    }

    // perform binary search.
    private int[] binarySearch(List<int[]> numIndexList, int index){
        int[] result = new int[] {-1,0};
        int low = 0;
        int high = numIndexList.size() -1;
        if (index < numIndexList.get(low)[0] || index > numIndexList.get(high)[0]) return result;

        while(low <= high){
            int mid = (low+high)/2;
            if (numIndexList.get(mid)[0] == index){
                return numIndexList.get(mid);
            } else if (numIndexList.get(mid)[0] > index){
                high = mid -1;
            } else {
                low = mid+1;
            }
        }
        return result;
    }
}
