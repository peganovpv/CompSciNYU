public class Recursion {
    
    public static int factorial(int n) throws IllegalArgumentException{
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n == 0) {
            return 1;
        }
        return n * factorial(n-1);
    }

    public static int fibonacci(int n) throws IllegalArgumentException{
        if (n < 0) {
            throw new IllegalArgumentException("n must be >= 0");
        }
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args){
        System.out.println("factorial(5) = " + factorial(5));
        System.out.println("fibonacci(5) = " + fibonacci(5));
    }
}
