public class Main{

    public static void main(String[] args){

        int[] arr = new int[1000];

        int numComparisons_Bubble = BubbleSort(arr);
        System.out.println("Number of comparisons (Bubble Sort): " + numComparisons);

        int numComparisons_Selection = SelectionSort(arr);
        System.out.println("Number of comparisons (Selection Sort): " + numComparisons);
        
    }

    private static int BubbleSort(int[] arr){

        int numComparisons = 0;

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                numComparisons++;
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j] = temp;
                }
            }
        }

        return numComparisons;

    }

    private static int SelectionSort(int[] arr){

        int numComparisons = 0;

        for(int i = 0; i < arr.length; i++){
            int minIndex = i;
            for(int j = i; j < arr.length; j++){
                numComparisons++;
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

        return numComparisons;

    }


}