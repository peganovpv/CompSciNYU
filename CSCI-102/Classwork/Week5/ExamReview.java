import java.util.*;

public class ExamReview {
    
    public int RecursiveMethod(int x){
        
        if (x == 0) { // base case
            return 0;
        } else if (x % 10 == 7) { // check if the remainder is 7
            return 1 + RecursiveMethod(x / 10); // take off the last digit and add 1
        } else {
            return RecursiveMethod(x / 10); // take off the last digit
        }
    }

    public static void main(String[] args) {
        ExamReview examReview = new ExamReview();
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int x = scn.nextInt();
        System.out.println(examReview.RecursiveMethod(x));
        scn.close();
    }

}
