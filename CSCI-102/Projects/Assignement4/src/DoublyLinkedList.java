import java.util.ArrayList;

public class DoublyLinkedList<E> {
 
   //---------------- nested Node class ----------------
   /**
    * Node of a doubly linked list, which stores a reference to its
    * element and to both the previous and next node in the list.
    */
   private static class Node<E> {
 
     /** The element stored at this node */
     private E element;               // reference to the element stored at this node
 
     /** A reference to the preceding node in the list */
     private Node<E> prev;            // reference to the previous node in the list
 
     /** A reference to the subsequent node in the list */
     private Node<E> next;            // reference to the subsequent node in the list
    
     private Node<E> north; // reference to the north node
     private Node<E> east;  // reference to the east node
     private Node<E> south; // reference to the south node
     private Node<E> west;  // reference to the west node

     /**
      * Creates a node with the given element and next node.
      *
      * @param e  the element to be stored
      * @param p  reference to a node that should precede the new node
      * @param n  reference to a node that should follow the new node
      */
     public Node(E e, Node<E> p, Node<E> n) {
       element = e;
       prev = p;
       next = n;
     }

     public Node(E e, Node<E> p, Node<E> n, Node<E> north, Node<E> east, Node<E> south, Node<E> west) {
        this(e, p, n);
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }
 
     // public accessor methods

     public Node<E> getNorth() { return north; }
     public void setNorth(Node<E> n) { north = n; }
     public Node<E> getEast() { return east; }
     public void setEast(Node<E> e) { east = e; }
     public Node<E> getSouth() { return south; }
     public void setSouth(Node<E> s) { south = s; }
     public Node<E> getWest() { return west; }
     public void setWest(Node<E> w) { west = w; }
     /**
      * Returns the element stored at the node.
      * @return the element stored at the node
      */
     public E getElement() { return element; }
 
     /**
      * Returns the node that precedes this one (or null if no such node).
      * @return the preceding node
      */
     public Node<E> getPrev() { return prev; }
 
     /**
      * Returns the node that follows this one (or null if no such node).
      * @return the following node
      */
     public Node<E> getNext() { return next; }
 
     // Update methods
     /**
      * Sets the node's previous reference to point to Node n.
      * @param p    the node that should precede this one
      */
     public void setPrev(Node<E> p) { prev = p; }
 
     /**
      * Sets the node's next reference to point to Node n.
      * @param n    the node that should follow this one
      */
     public void setNext(Node<E> n) { next = n; }
   } //----------- end of nested Node class -----------
 
   // instance variables of the DoublyLinkedList
   /** Sentinel node at the beginning of the list */
   private Node<E> header;                    // header sentinel
 
   /** Sentinel node at the end of the list */
   private Node<E> trailer;                   // trailer sentinel
 
   /** Number of elements in the list (not including sentinels) */
   private int size = 0;                      // number of elements in the list
   
   private Node<E> root;
   private Node<E> currentPosition;

   public void Root(E letter) {
       root = new Node<>(letter, null, null, null, null, null, null);
       currentPosition = root;
   }

   /** Constructs a new empty list. */
   public DoublyLinkedList() {
     header = new Node<>(null, null, null);      // create header
     trailer = new Node<>(null, header, null);   // trailer is preceded by header
     header.setNext(trailer);                    // header is followed by trailer
   }
 
   // public accessor methods
   /**
    * Returns the number of elements in the linked list.
    * @return number of elements in the linked list
    */
   public int size() { return size; }
 
   /**
    * Tests whether the linked list is empty.
    * @return true if the linked list is empty, false otherwise
    */
   public boolean isEmpty() { return size == 0; }
 
   /**
    * Returns (but does not remove) the first element of the list.
    * @return element at the front of the list (or null if empty)
    */
   public E first() {
     if (isEmpty()) return null;
     return header.getNext().getElement();   // first element is beyond header
   }
 
   /**
    * Returns (but does not remove) the last element of the list.
    * @return element at the end of the list (or null if empty)
    */
   public E last() {
     if (isEmpty()) return null;
     return trailer.getPrev().getElement();    // last element is before trailer
   }
 
   // public update methods
   /**
    * Adds an element to the front of the list.
    * @param e   the new element to add
    */
   public void addFirst(E e) {
     addBetween(e, header, header.getNext());    // place just after the header
   }
 
   /**
    * Adds an element to the end of the list.
    * @param e   the new element to add
    */
   public void addLast(E e) {
     addBetween(e, trailer.getPrev(), trailer);  // place just before the trailer
   }
 
   /**
    * Removes and returns the first element of the list.
    * @return the removed element (or null if empty)
    */
   public E removeFirst() {
     if (isEmpty()) return null;                  // nothing to remove
     return remove(header.getNext());             // first element is beyond header
   }
 
   /**
    * Removes and returns the last element of the list.
    * @return the removed element (or null if empty)
    */
   public E removeLast() {
     if (isEmpty()) return null;                  // nothing to remove
     return remove(trailer.getPrev());            // last element is before trailer
   }
 
   // private update methods
   /**
    * Adds an element to the linked list in between the given nodes.
    * The given predecessor and successor should be neighboring each
    * other prior to the call.
    *
    * @param predecessor   node just before the location where the new element is inserted
    * @param successor     node just after the location where the new element is inserted
    */
   private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
     // create and link a new node
     Node<E> newest = new Node<>(e, predecessor, successor);
     predecessor.setNext(newest);
     successor.setPrev(newest);
     size++;
   }
 
   /**
    * Removes the given node from the list and returns its element.
    * @param node    the node to be removed (must not be a sentinel)
    */
   private E remove(Node<E> node) {
     Node<E> predecessor = node.getPrev();
     Node<E> successor = node.getNext();
     predecessor.setNext(successor);
     successor.setPrev(predecessor);
     size--;
     return node.getElement();
   }
   
   // public void method in the DoublyLinked List class that swaps two non adjacent (and not header and tail  nodes), given the index of the elements
   public void swap(int n, int m) {

        if (n == m) return; // if indices are the same, no swap is needed

        // find the nodes at position n and m
        Node<E> nodeN = header.getNext();
        for (int i = 0; i < n; i++){
            nodeN = nodeN.getNext();
        }
        Node<E> nodeM = header.getNext();
        for (int i = 0; i < m; i++){
            nodeM = nodeM.getNext();
        }

        // swap the nodes
        Node<E> temp = nodeN.getNext();
        nodeN.setNext(nodeM.getNext());
        nodeM.setNext(temp);

        temp = nodeN.getPrev();
        nodeN.setPrev(nodeM.getPrev());
        nodeM.setPrev(temp);

        // update the references to these nodes
        nodeN.getNext().setPrev(nodeN);
        nodeN.getPrev().setNext(nodeN);

        nodeM.getNext().setPrev(nodeM);
        nodeM.getPrev().setNext(nodeM);

}
   
   /**
    * Produces a string representation of the contents of the list.
    * This exists for debugging purposes only.
    */
   public String toString() {
     StringBuilder sb = new StringBuilder("(");
     Node<E> walk = header.getNext();
     while (walk != trailer) {
       sb.append(walk.getElement());
       walk = walk.getNext();
       if (walk != trailer)
         sb.append(", ");
     }
     sb.append(")");
     return sb.toString();
   }

   public void Add(E letter, String direction) {
      Node<E> newNode = new Node<>(letter, null, null, null, null, null, null);
      switch (direction.toUpperCase()) {
            case "NORTH": currentPosition.setNorth(newNode); break;
            case "EAST": currentPosition.setEast(newNode); break;
            case "SOUTH": currentPosition.setSouth(newNode); break;
            case "WEST": currentPosition.setWest(newNode); break;
        }
    }
   public void Move(String direction) {
      switch (direction.toUpperCase()) {
        case "NORTH": currentPosition = currentPosition.getNorth(); break;
        case "EAST": currentPosition = currentPosition.getEast(); break;
        case "SOUTH": currentPosition = currentPosition.getSouth(); break;
        case "WEST": currentPosition = currentPosition.getWest(); break;
      }
    }

    public void Print() {

        p(root);
        System.out.println();
    }

    private void p(Node<E> node) {
      
        if (node == null) {
            return;
        }

        System.out.print(node.getElement() + " ");

        p(node.getNorth());
        p(node.getEast());
        p(node.getSouth());
        p(node.getWest());

    }

    public void PrintLewisStructure() {
        ArrayList<Node<E>> visited = new ArrayList<>();
        pls(root, visited, "");
    }
    
    private void pls(Node<E> node, ArrayList<Node<E>> visited, String direction) {
        if (node == null || visited.contains(node)) {
            return;
        }
        visited.add(node);
    
        if (direction.equals("East")) {
            System.out.print("--" + node.getElement());
        } else if (direction.equals("South")) {
            System.out.println("|");
            System.out.print(" " + node.getElement());
        } else {
            System.out.print(node.getElement());
        }
    
        pls(node.getNorth(), visited, "North");
        pls(node.getEast(), visited, "East");
        pls(node.getSouth(), visited, "South");
        pls(node.getWest(), visited, "West");
    }
 } //----------- end of DoublyLinkedList class -----------
 