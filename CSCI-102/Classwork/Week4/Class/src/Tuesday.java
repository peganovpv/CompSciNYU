package src;

import java.util.Arrays;

public class Tuesday {
    
    public interface Stack<E> {
        int size();

        boolean isEmpty();

        void push(E e);

        E top();

        E pop();
    }

    public static class ArrayStack<E> implements Stack<E> {
        public static final int CAPACITY = 1000;
        private E[] data;
        private int t = -1;

        public ArrayStack() {
            this(CAPACITY);
        }

        public ArrayStack(int capacity) {
            data = (E[]) new Object[capacity];
        }

        public int size() {
            return (t + 1);
        }

        public boolean isEmpty() {
            return (t == -1);
        }

        public void push(E e) {
            if (size() == data.length)
                throw new IllegalStateException("Stack is full");
            data[++t] = e;
        }

        public E top() {
            if (isEmpty())
                return null;
            return data[t];
        }

        public E pop() {
            if (isEmpty())
                return null;
            E answer = data[t];
            data[t] = null;
            t--;
            return answer;
        }
    }


    public static void main(String[] args){
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        // log time in milliseconds
        System.out.println(System.currentTimeMillis());
        for(int i = 0; i < 100; i++){
            stack.push(i);
            System.out.println(null == stack.top() ? "null" : stack.top());
        }
        System.out.println(System.currentTimeMillis());
    }

    

}
