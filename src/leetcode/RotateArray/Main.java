package leetcode.RotateArray;

import java.util.Arrays;

/**
 * @program: 2023_AI_HW
 * @description:
 * @create: 2023-09-30 00:43
 **/
public class Main {
    public static void main(String[] args) {
        int[] test = {1,2,3,4,5,6};
        int k = 5;
        Solution solve = new Solution();
        solve.rotate(test,k);
        System.out.println(Arrays.toString(test));
    }
}

