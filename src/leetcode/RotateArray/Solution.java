package leetcode.RotateArray;

/**
 * @program: 2023_AI_HW
 * @description: Solution to RotateArray
 * @create: 2023-09-30 00:39
 **/
class Solution {
    public void rotate(int[] nums, int k) {
        int tmp = 0;
        if(k < nums.length){
            for(int i = 0; i < nums.length; i++){
                if(i < nums.length - k){
                    nums[i] =0;
                }
            }
        }else if(k > nums.length){

        }
    }
}

