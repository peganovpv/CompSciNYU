import java.util.Random;

public class Main {

    private static int selectionSort(Student[] arr){

        int numComparisons = 0;

        for(int i = 0; i < arr.length; i++){

            int minIndex = i;

            for(int j = i+1; j < arr.length; j++){

                numComparisons++;

                if(arr[j].compareTo(arr[minIndex]) < 0){
                    minIndex = j;
                }

            }

            Student temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;


        }

        return numComparisons;

    }

    public static void main(String[] args){

        Main m = new Main();

        Student[] students = new Student[1000];
        Student[] students2 = new Student[1000];

        for(int i = 0; i < students.length; i++){

            Random r = new Random();
            // random 8 digit number
            int id = r.nextInt(90000000) + 10000000;
            // random name
            String name = "";
            for(int j = 0; j < 5; j++){
                name += (char)(r.nextInt(26) + 'a');
            }
            // random major
            String major = "";
            for(int j = 0; j < 3; j++){
                major += (char)(r.nextInt(26) + 'a');
            }
            // create student
            students[i] = new Student(name, "N"+id , major);

        }
        for(int i = 0; i < students2.length; i++){

            Random r = new Random();
            // random 8 digit number
            int id = r.nextInt(90000000) + 10000000;
            // random name
            String name = "";
            for(int j = 0; j < 5; j++){
                name += (char)(r.nextInt(26) + 'a');
            }
            // random major
            String major = "";
            for(int j = 0; j < 3; j++){
                major += (char)(r.nextInt(26) + 'a');
            }
            // create student
            students2[i] = new Student(name, "N"+id , major);

        }

        // selection sort students
        int numComparisons1 = selectionSort(students);
        int numComparisons2 = selectionSort(students2);
        System.out.println("Selection Sort Comparisons (Array 1): " + numComparisons1);
        System.out.println("Selection Sort Comparisons (Array 2): " + numComparisons2);

        // merge students
        Student[] merged = m.merge(students, students2);

        // selection sort merged students
        int numComparisons3 = selectionSort(merged);
        System.out.println("Selection Sort Comparisons (Merged Array): " + numComparisons3);

        // merge sort students
        int numComparisons4 = m.mergeSort(merged);
        System.out.println("Merge Sort Comparisons (Merged Array): " + numComparisons4);

    }

    public Student[] merge(Student[] arr1, Student[] arr2){
            
            Student[] merged = new Student[arr1.length + arr2.length];
    
            int i = 0;
            int j = 0;
            int k = 0;
    
            while(i < arr1.length && j < arr2.length){
    
                if(arr1[i].compareTo(arr2[j]) < 0){
                    merged[k] = arr1[i];
                    i++;
                } else {
                    merged[k] = arr2[j];
                    j++;
                }
    
                k++;
    
            }
    
            while(i < arr1.length){
                merged[k] = arr1[i];
                i++;
                k++;
            }
    
            while(j < arr2.length){
                merged[k] = arr2[j];
                j++;
                k++;
            }
    
            return merged;
    
    }

    public int mergeSort(Student[] arr){

        if(arr.length <= 1){
            return 0;
        }

        int numComparisons = 0;

        int mid = arr.length / 2;

        Student[] left = new Student[mid];
        Student[] right = new Student[arr.length - mid];

        for(int i = 0; i < mid; i++){
            left[i] = arr[i];
        }

        for(int i = mid; i < arr.length; i++){
            right[i - mid] = arr[i];
        }

        numComparisons += mergeSort(left);
        numComparisons += mergeSort(right);

        Student[] merged = merge(left, right);

        for(int i = 0; i < merged.length; i++){
            arr[i] = merged[i];
        }

        return numComparisons;

    }

}