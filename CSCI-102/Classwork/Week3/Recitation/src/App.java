package src;

public class App {

    public static void main(String[] args){

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        for(int i = 0; i < 10; i ++){
            list.addLast(i);
        }

        // swap two elements
        list.swap(1, 2);

        System.out.println(list);
    }
}
