import java.util.*;

public class Main {

    public int[] MergeSort(int[] arr){
        if(arr.length <= 1){
            return arr;
        }
        int[] left = new int[arr.length/2];
        int[] right = new int[arr.length - left.length];
        for(int i = 0; i < left.length; i++){
            left[i] = arr[i];
        }
        for(int i = 0; i < right.length; i++){
            right[i] = arr[i + left.length];
        }
        left = MergeSort(left);
        right = MergeSort(right);
        return Merge(left, right);
    }

    public static void main(String[] args){

        Random r = new Random();
        int[] arr = new int[25];

        for(int i = 0; i < arr.length; i++){
            arr[i] = r.nextInt(100);
        }

        Main m = new Main();
        arr = m.MergeSort(arr);

        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
        
    }

}