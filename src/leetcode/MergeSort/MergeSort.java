package leetcode.MergeSort;

/**
 * @program: 2023_AI_HW
 * @description: inplementation of Merge Sort
 * @create: 2023-10-26 16:43
 **/
public class MergeSort {

    public static void merge(int[] A, int[] C, int p, int mid, int q){
        int i = p;
        int j = mid;
        for (int k = p; k < q; k++) {C[k] = A[k];}

        for (int k = p; k < q; k++){
            if (i >= mid){A[k] = C[j++];} // append rest A[q] to C
            else if (j >= q){A[k] = C[i++];} // append rest A[p] to C
            else if (C[i] <= C[j]){A[k] = C[i++];}
            else {A[k] = C[j++];}
        }

    }
    public static void sort(int[] A, int[] C, int p, int q){
        if (p + 1 < q){ // array is [),
            //get mid
            int mid = p + (q - p)/2;
            //sort left and right, then merge
            sort(A, C, p, mid);
            sort(A, C, mid, q);
            merge(A, C, p, mid, q);
        }
    }
    public static void sort(int[] A){
        int[] C = new int[A.length];
        sort(A, C,0, A.length);
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,5,9,4,2,63,7,89,0,3};
        MergeSort.sort(A);
        for (int i:
             A) {
            System.out.println(i);
        }
    }
}

