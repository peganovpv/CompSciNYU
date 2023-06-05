package Week3;

public class Recitation {
    public class SinglyLinkedList<E>{

        private class Node<E> {
            private E element;
            private Node<E> next;
            public Node(E e, Node<E> n) {
                element = e; 
                next = n;
            }
            
            public E getElement() {
                return element;
            }

            public Node<E> getNext() {
                return next;
            }

            public void setNext(Node<E> next) {
                this.next = next;
            }
        }

        private Node<E> head = null;
        private Node<E> tail = null;

        private int size = 0;

        public SinglyLinkedList(){}

        public int size() {
            return size;
        }
        public boolean isEmpty(){
            return size == 0;
        }

        public E first(){
            if(isEmpty()){
                return null;
            } else {
                return head.getElement();
            }
        }
        public E last() {
            if (isEmpty()) return null;
            return tail.getElement(); 
        }

        public void addFirst(E e) {
            head = new Node<>(e, head); 
            if (size == 0)
                tail = head; 
            size++;
        }

        public void addLast(E e){
            Node<E> newest = new Node<>(e, null);
            if(isEmpty()){
                head = newest;
            } else {
                tail.setNext(newest);
            }
            tail = newest;
            size++;
        }

        public E removeFirst(){
            if(isEmpty()){
                return null;
            } else {
                E answer = head.getElement();
                head = head.getNext();
                size--;
                if(size == 0){
                    tail = null;
                }
                return answer;
            }
        }

        private Node<E> getNode(int index) {
            Node<E> current = head;
            for(int i = 0; i < index && current != null; i++) {
                current = current.getNext();
            }
            return current;
        }

        public void swapTwo(int index1, int index2){
            Node<E> node1 = getNode(index1);
            Node<E> node2 = getNode(index2);
            Node<E> prev1 = getNode(index1 - 1);
            Node<E> prev2 = getNode(index2 - 1);

            if(prev1 != null) prev1.setNext(node2);
            if(prev2 != null) prev2.setNext(node1);

            Node<E> temp = node1.getNext();
            node1.setNext(node2.getNext());
            node2.setNext(temp);

            if(index1 == 0) head = node2;
            if(index2 == 0) head = node1;
            if(index1 == size - 1) tail = node2;
            if(index2 == size - 1) tail = node1;
        }

        public void RemoveAtEnd(int number){
            int targetIndex = size - number - 1;
            getNode(targetIndex).setNext(null);
            tail = getNode(targetIndex);
            size -= number;
        }

    }
}
