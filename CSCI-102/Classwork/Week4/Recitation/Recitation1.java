public class Recitation1 {

    public static void main(String[] args) {

        // populate stack with 10 elements (integers from 1 - 100)

        ArrayStack S = new ArrayStack();
        ArrayStack T = new ArrayStack();
        ArrayStack R = new ArrayStack();

        for (int i = 1; i <= 10; i++) {
            S.push(i);
        }
        for(int i = 11; i <= 20; i++) {
            T.push(i);
        }
        for(int i = 21; i <= 30; i++) {
            R.push(i);
        }

        S.printStack();
        T.printStack();
        R.printStack();

        // S: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        // T: [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
        // R: [21, 22, 23, 24, 25, 26, 27, 28, 29, 30]

        // store all of T's elements in S below S's elements, both sets having their element in original order

        while(!T.isEmpty()) {
            S.push(T.pop());
        }

        S.printStack();
        T.printStack();
        R.printStack();

    }

}
