public class ArrayStack implements Stack{

    public static final int CAPACITY = 1000;

    private Object[] data;

    private int t = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

    public ArrayStack(int capacity) {
        data = new Object[capacity];
    }

    public int size() {
        return (t + 1);
    }

    public boolean isEmpty() {
        return (t == -1);
    }

    public void push(Object e) {
        if (size() == data.length)
            throw new IllegalStateException("Stack is full");
        data[++t] = e;
    }

    public Object top() {
        if (isEmpty())
            return null;
        return data[t];
    }

    public Object pop() {
        if (isEmpty())
            return null;
        Object answer = data[t];
        data[t] = null;
        t--;
        return answer;
    }

    // print contents of stack

    public void printStack() {
        System.out.print("[");
        for (int i = 0; i < size(); i++) {
            System.out.print(data[i]);
            if (i != size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

}
