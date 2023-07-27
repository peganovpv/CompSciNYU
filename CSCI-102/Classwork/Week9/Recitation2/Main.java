import java.util.*;

public class Main {
    
    //Randomly populate arrays of size 100 containing integers from 1 to 1000. Send the arrays to the Heap Sort. Use a copy of the Heap Sort class from the sourcecode jar file. Change the Heap Sort class to count the number of upheap swaps and downheap swaps. Print the final number of swaps of each type performed during the Heap sort call. You can make a static class that tracks the counts, so they are available when you return from the Heap sort call.

    public static void main(String[] args) {
        int[] arr = new int[100];
        Random rand = new Random();
        for(int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000) + 1;
        }
        HeapPriorityQueue<Integer, Integer> heap = new HeapPriorityQueue<Integer, Integer>();
        for(int i = 0; i < arr.length; i++){
            heap.insert(arr[i], arr[i]);
        }
    }   

}
