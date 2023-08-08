public class Main {
    // Bucket sort
    public static void main(String[] args) {
        Main m = new Main();
        int[] arr = {1, 4, 1, 2, 7, 5, 2};
        m.BucketSort(arr);
    }

    public int[] BucketSort(int[] arr){
        // bucket sort works by creating a bucket of size 10
        // we need to run the loop for the length of the array
        // we need to find the digit at the ith place

        int[] bucket = new int[10];

        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                System.out.print(i + " ");
            }
        }

        return arr;

    }

    public int[] RadixSort(int[] arr){
        // radix sort works on the basis of the number of digits in the number
        // we need to find the number of digits in the largest number
        // then we need to run the loop for that many times

        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
        }

        int digits = 0;
        while(max>0){
            max = max/10;
            digits++;
        }

        // now we need to run the loop for the number of digits

        for (int i = 0; i < digits; i++) {
            // we need to create a bucket of size 10
            int[] bucket = new int[10];
            // we need to run the loop for the length of the array
            for (int j = 0; j < arr.length; j++) {
                // we need to find the digit at the ith place
                int digit = (arr[j]/(int)Math.pow(10,i))%10;
                bucket[digit]++;
            }
            // we need to find the prefix sum of the bucket
            for (int j = 1; j < bucket.length; j++) {
                bucket[j] = bucket[j] + bucket[j-1];
            }
            // we need to create a new array
            int[] temp = new int[arr.length];
            // we need to run the loop in reverse order
            for (int j = arr.length-1; j >=0 ; j--) {
                // we need to find the digit at the ith place
                int digit = (arr[j]/(int)Math.pow(10,i))%10;
                // we need to find the index of the digit in the bucket
                int index = bucket[digit];
                // we need to place the element at the index in the temp array
                temp[index-1] = arr[j];
                // we need to decrement the index in the bucket
                bucket[digit]--;
            }
            // we need to copy the temp array to the original array
            for (int j = 0; j < arr.length; j++) {
                arr[j] = temp[j];
            }
        }

        return arr;
    }

}
