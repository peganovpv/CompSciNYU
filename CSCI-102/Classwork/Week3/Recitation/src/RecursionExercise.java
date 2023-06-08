public class RecursionExercise{

    // call a recursive routine that returns the string in reverse order AND removes vowels(a,e,i,o,u).

    public static void main(String[] args){
        System.out.println(reverse("hello"));
        System.out.println(findSmallest(new int[]{1,2,3,4,5,6,7,8,9,10}, 9));
        System.out.println(convert(12439));
    }

    // reverse the string and remove vowels
    public static String reverse(String str){
        if(str.length() == 0){
            return str;
        }
        else{
            char c = str.charAt(0);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'){
                return reverse(str.substring(1));
            }
            else{
                return reverse(str.substring(1)) + c;
            }
        }
    }

    // Populate a 20 element array with random numbers from 1 to 100. Write a recursive routine to find the smallest number.
    public static int findSmallest(int[] arr, int index){
        if(index == 0){
            return arr[index];
        }
        else{
            int smallest = findSmallest(arr, index - 1);
            if(arr[index] < smallest){
                return arr[index];
            }
            else{
                return smallest;
            }
        }
    }

    // Develop a recursive algorithm  for converting an integer into a string of digits. For example the integer 12439 would "12439".
    public static String convert(int num){
        if(num < 10){
            return "" + num;
        }
        else{
            return convert(num / 10) + num % 10;
        }
    }

}