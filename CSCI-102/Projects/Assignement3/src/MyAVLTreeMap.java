import java.util.Arrays;
import java.util.Comparator;

import net.datastructures.*;

public class MyAVLTreeMap<K,V> extends TreeMap<K,V> {

	
  /** Constructs an empty map using the natural ordering of keys. */
  public MyAVLTreeMap() { 
	  super(); 	
  }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public MyAVLTreeMap(Comparator<K> comp) { super(comp); }

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

/**
 * Display AVL tree on console.
 */
public void printTree() {

  // Define 2D grid to represent the tree
  char[][] grid = new char[100][100];

  // Obtain the root of the tree
  Position<Entry<K,V>> rootPos = this.root();

  // Set initial positions
  int yPos = 0;
  int xPos = 32;

  // Begin tree traversal from root
  traverseTree(rootPos, yPos, xPos, 16, grid);

  // Loop through grid and print tree
  for (int i = 0; i < grid.length; i++) {
    for (int j = 0; j < grid[i].length; j++) {
      char currentCell = grid[i][j];
      if (currentCell != 0) {
        System.out.print(grid[i][j]);
      } else {
        System.out.print(" ");
      }
    }
    System.out.println("");
  }
}

/**
* Traverses the tree recursively and fills values in grid.
* @param node, is the current node
* @param yPos, is the current y position in the grid
* @param xPos, is the current x position in the grid
* @param distance, is horizontal length from children to parent
*/
public void traverseTree(Position<Entry<K,V>> node, int yPos, int xPos, int distance, char[][] grid) {

  // Stop if current node is null
  if (node.getElement() == null) {
      return;
  }

  // Get key from the node and store its first character in grid
  K key = node.getElement().getKey();
  char keyChar = key.toString().charAt(0);
  grid[yPos][xPos] = keyChar; 

  // Recursion on left subtree if it exists
  if (left(node).getElement() != null) {
      grid[yPos + 1][xPos - (distance / 2)] = '/';
      Position<Entry<K,V>> leftBranch = left(node);
      int yPosLeft = yPos + 2;
      int xPosLeft = xPos - distance;
      traverseTree(leftBranch, yPosLeft, xPosLeft, distance / 2, grid);
  } 

  // Recursion on right subtree if it exists
  if (right(node).getElement() != null) {
      grid[yPos + 1][xPos + (distance / 2)] = '\\';
      Position<Entry<K,V>> rightBranch = right(node);
      int yPosRight = yPos + 2;
      int xPosRight = xPos + distance;
      traverseTree(rightBranch, yPosRight, xPosRight, distance / 2, grid);
  }
}


}
