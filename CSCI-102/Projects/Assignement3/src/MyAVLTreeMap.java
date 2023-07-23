package com;


import java.util.Arrays;
import java.util.Comparator;

import net.datastructures.*;

public class MyAVLTreeMap<K,V> extends TreeMap<K,V> {
	
	private String[][] arr; 
	
  /** Constructs an empty map using the natural ordering of keys. */
  public MyAVLTreeMap() { 
	  super(); 	
	  arr = new String[100][100];
	  for (String[] row : arr) { // filling with spaces
	    Arrays.fill(row, " ");
	  }
  }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public MyAVLTreeMap(Comparator<K> comp) { super(comp); }
  
  MyAVLTreeMap tree;

  /** Returns the height of the given tree position. */
  protected int height(Position<Entry<K,V>> p) {
    return tree.getAux(p);
  }

  /** Recomputes the height of the given position based on its children's heights. */
  protected void recomputeHeight(Position<Entry<K,V>> p) {
    tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
  }

  /** Returns whether a position has balance factor between -1 and 1 inclusive. */
  protected boolean isBalanced(Position<Entry<K,V>> p) {
    return Math.abs(height(left(p)) - height(right(p))) <= 1;
  }

  /** Returns a child of p with height no smaller than that of the other child. */
  protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
    if (height(left(p)) > height(right(p))) return left(p);     // clear winner
    if (height(left(p)) < height(right(p))) return right(p);    // clear winner
    // equal height children; break tie while matching parent's orientation
    if (isRoot(p)) return left(p);                 // choice is irrelevant
    if (p == left(parent(p))) return left(p);      // return aligned child
    else return right(p);
  }

  /**
   * Utility used to rebalance after an insert or removal operation. This traverses the
   * path upward from p, performing a trinode restructuring when imbalance is found,
   * continuing until balance is restored.
   */
  protected void rebalance(Position<Entry<K,V>> p) {
    int oldHeight, newHeight;
    do {
      oldHeight = height(p);                       // not yet recalculated if internal
      if (!isBalanced(p)) {                        // imbalance detected
        // perform trinode restructuring, setting p to resulting root,
        // and recompute new local heights after the restructuring
        p = restructure(tallerChild(tallerChild(p)));
        recomputeHeight(left(p));
        recomputeHeight(right(p));
      }
      recomputeHeight(p);
      newHeight = height(p);
      p = parent(p);
    } while (oldHeight != newHeight && p != null);
  }

  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
  @Override
  protected void rebalanceInsert(Position<Entry<K,V>> p) {
    rebalance(p);
  }

  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
  @Override
  protected void rebalanceDelete(Position<Entry<K,V>> p) {
    if (!isRoot(p))
      rebalance(parent(p));
  }

  /** Ensure that current tree structure is valid AVL (for debug use only). */
  private boolean sanityCheck() {
    for (Position<Entry<K,V>> p : tree.positions()) {
      if (isInternal(p)) {
        if (p.getElement() == null)
          System.out.println("VIOLATION: Internal node has null entry");
        else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
          System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
          dump();
          return false;
        }
      }
    }
    return true;
  }

  
  public void printAVLTree() {
      printTree(0, 50, 0, this.root()); // start from the root
      for (String[] row : arr) { // printing 2D array
          for (String cell : row)
              System.out.print(cell);
          System.out.println();
      }
  }

  private void printTree(int height, int column, int direction, Position<Entry<K, V>> p) {
      if (p == null || p.getElement() == null) 
          return;
      
      arr[height][column] = p.getElement().getKey().toString(); // current node
      
      int newColumn;
      if (direction == -1) { // left child
          newColumn = column - (50 / (height + 2)); // calculate new column
          arr[height + 1][newColumn] = "/"; // print slash
      } else if (direction == 1) { // right child
          newColumn = column + (50 / (height + 2)); // calculate new column
          arr[height + 1][newColumn] = "\\"; // print backslash
      } else { // root node
          newColumn = column;
      }

      printTree(height + 2, newColumn, -1, this.left(p)); // left child
      printTree(height + 2, newColumn, 1, this.right(p)); // right child
  }
 
}
