public class Main {
    // Bucket sort
    public static void main(String[] args) {
        int[] arr = {5, 9, 10, 4, 7, 12, 19, 13, 0, 1, 3};
        int[] sorted = BucketSort(arr);
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }
    }

    public int[] BucketSort(int[] arr){

        int[] bucket = new int[10];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                System.out.print(i + " ");
            }
        } 
    }
}
