import java.util.*;

public class Main {

    public int[] randomArray(int size){
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = (int)(Math.random() * 100);
        }
        return array;
    }

    public int randomChoice(){
        int[] choices = {-1, 0, 1, 100};
        int random = (int)(Math.random() * 3);
        return choices[random];
    }

    public int findAverage(ArrayList<Integer> nums){
        int sum = 0;
        for(int i = 0; i < nums.size(); i++){
            sum += nums.get(i);
        }
        return sum / nums.size();
    }

    public static void main(String[] args){

        Main m = new Main();

        QuickSort q = new QuickSort();

        ArrayList<Integer> nums = new ArrayList<>(null);

        Comparator<Integer> comp = new Comparator<>();

        for(int i = 0; i < 100; i++){

            int choice = m.randomChoice();
            int[] array = m.randomArray(100);

            int x = q.quickSort(array, comp, choice);

            nums.add(x);

        }

        int average = m.findAverage(nums);

        System.out.println("Average number of comparisons: " + average);

    }

}

