import java.util.Random;

public class Main{

    public static void main(String[] args){

        Random r = new Random();

        int[] arr = new int[1000];

        for(int i = 0; i < 1000; i++){
            arr[i] = r.nextInt(1000);
        }

        int numComparisons_Bubble = BubbleSort(arr);
        System.out.println("Number of comparisons (Bubble Sort): " + numComparisons);

        int numComparisons_Selection = SelectionSort(arr);
        System.out.println("Number of comparisons (Selection Sort): " + numComparisons);

        // Array that favours Bubble Sort
        int [] bubbleSortArray = {
            1, 3, 2, 4, 5, 6, 7, 9, 8, 11, 10, 13, 12, 14, 16, 15, 17, 19, 18, 20
        };
        int numComparisons_Bubble_Favour = BubbleSort(bubbleSortArray);
        System.out.println("Number of comparisons (Bubble Sort Favour Array): " + numComparisons_Bubble_Favour);

        // Array that favours Selection Sort
        int [] selectionSortArray = {
            20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1
        };
        int numComparisons_Selection_Favour = SelectionSort(selectionSortArray);
        System.out.println("Number of comparisons (Selection Sort Favour Array): " + numComparisons_Selection_Favour);

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