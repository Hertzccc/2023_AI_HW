package leetcode.BinarySearch;

/**
 * @program: 2023_AI_HW
 * @description: Binary search(if not found, return the index the target should be)
 * @create: 2023-10-26 16:01
 **/
class Solution {
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        return bSearch(target, nums, i, j);
    }

    public int bSearch(int x, int[] A, int i, int j){
        if(i > j) return i;

        int m = (i + j)/2;
        if(A[m] == x) return m;
        else if(A[m] > x){
            return bSearch(x, A, i, m - 1);
        }
        else {
            return bSearch(x, A, m + 1, j);
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums1 = new int[]{1,3,5,6};
        int[] targets = new int[]{5, 2, 7};
        int[] expects = new int[]{2, 1, 4};
        for (int i = 0; i < targets.length; i++) {
            int ans = sol.searchInsert(nums1,targets[i]);
            System.out.println("expects" + i +"=" +expects[i]+ " and solution="+ ans+" is "+
                    (expects[i] == ans));

        }

    }

}


