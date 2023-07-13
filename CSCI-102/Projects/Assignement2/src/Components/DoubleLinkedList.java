package Components;
public class DoubleLinkedList<E> {

    // Node class for Doubly Linked List
    public static class Node<E> {
        public E element;             // Element stored in the node
        public Node<E> prev;         // Reference to the previous node in the list
        public Node<E> next;         // Reference to the next node in the list

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() { //getting the element
            return element;
        }

        public Node<E> getPrev() { //getting previous node
            return prev;
        }

        public Node<E> getNext() { // getting next node
            return next;
        }

        public void setPrev(Node<E> p) { //setting previous node
            prev = p;
        }

        public void setNext(Node<E> n) { // setting next node 
            next = n;
        }
    }

    public Node<E> head;            // Header sentinel
    public Node<E> tail;            // Trailer sentinel
    private int size = 0;           // Number of elements in the list

    public DoubleLinkedList() {
        head = new Node<>(null, null, null);       // Create header
        tail = new Node<>(null, head, null);       // Trailer is preceded by the header
        head.setNext(tail);                        // Header is followed by the trailer
    }

    public int size() { 
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) return null;
        return head.getNext().getElement();    // First element is beyond the header
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getPrev().getElement();    // Last element is before the trailer
    }

    public void addFirst(E e) {
        addBetween(e, head, head.getNext());    // Place the new node just after the header
    }

    public void addLast(E e) {
        addBetween(e, tail.getPrev(), tail);     // Place the new node just before the trailer
    }

    public E removeFirst() {
        if (isEmpty()) return null;                // Nothing to remove
        return remove(head.getNext());          // Remove the first element beyond the header
    }

    public E removeLast() {
        if (isEmpty()) return null;                // Nothing to remove
        return remove(tail.getPrev());           // Remove the last element before the trailer
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        // Create and link a new node
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
    //creating the toString method
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = head.getNext();
        while (walk != tail) {
            sb.append(walk.getElement());
            walk = walk.getNext();
            if (walk != tail)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    private E remove(Node<E> node) { //removing the node
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

    public E getValueAt(int i){
        Node<E> walk = head.getNext();
        for (int j = 0; j < i; j++) {
            walk = walk.getNext();
        }
        return walk.getElement();
    }
}
