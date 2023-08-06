/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
import java.util.Comparator;
import net.datastructures.Queue;
import net.datastructures.LinkedQueue;

class QuickSort {

  //-------- support for top-down quick-sort of queues --------
  /** Quick-sort contents of a queue. */
  public static int quickSort(Queue<K> S, Comparator<K> comp, int m) {

    int numComparisons = 0;

    int n = S.size();
    if (n < 2) return 0;                       // queue is trivially sorted
    // divide
    K pivot;                                 // using first as arbitrary pivot
    if(m == 0){
        // first element
        pivot = S.first();
    } else if(m == 1){
        // last element
        pivot = S.last();
    } else if(m == -1){
        // middle element
        int middle = (int)(Math.ceil(n/2));
        for(int i = 0; i < middle; i++){
            pivot = S.dequeue();
            S.enqueue(pivot);
        }
    } else {
        // random element between first and last
        int random = (int)(Math.random() * (n - 1));
    }
    Queue<K> L = new LinkedQueue<>();
    Queue<K> E = new LinkedQueue<>();
    Queue<K> G = new LinkedQueue<>();
    while (!S.isEmpty()) {                   // divide original into L, E, and G
      K element = S.dequeue();
      int c = comp.compare(element, pivot);
      if (c < 0)                             // element is less than pivot
        L.enqueue(element);
      else if (c == 0)                       // element is equal to pivot
        E.enqueue(element);
      else                                   // element is greater than pivot
        G.enqueue(element);
    }
    // conquer
    quickSort(L, comp);                      // sort elements less than pivot
    quickSort(G, comp);                      // sort elements greater than pivot
    // concatenate results
    while (!L.isEmpty())
      numComparisons++;
      S.enqueue(L.dequeue());
    while (!E.isEmpty())
      numComparisons++;
      S.enqueue(E.dequeue());
    while (!G.isEmpty())
      numComparisons++;
      S.enqueue(G.dequeue());

    return numComparisons;
  }

  //-------- support for in-place quick-sort of an array --------
  /** Quick-sort contents of a queue. */
  public static <K> void quickSortInPlace(K[] S, Comparator<K> comp) {
    quickSortInPlace(S, comp, 0, S.length-1);
  }

  /** Sort the subarray S[a..b] inclusive. */
  private static <K> void quickSortInPlace(K[] S, Comparator<K> comp,
                                                                   int a, int b) {
    if (a >= b) return;                // subarray is trivially sorted
    int left = a;
    int right = b-1;
    K pivot = S[b];
    K temp;                            // temp object used for swapping
    while (left <= right) {
      // scan until reaching value equal or larger than pivot (or right marker)
      while (left <= right && comp.compare(S[left], pivot) < 0) left++;
      // scan until reaching value equal or smaller than pivot (or left marker)
      while (left <= right && comp.compare(S[right], pivot) > 0) right--;
      if (left <= right) {             // indices did not strictly cross
        // so swap values and shrink range
        temp = S[left]; S[left] = S[right]; S[right] = temp;
        left++; right--;
      }
    }
    // put pivot into its final place (currently marked by left index)
    temp = S[left]; S[left] = S[b]; S[b] = temp;
    // make recursive calls
    quickSortInPlace(S, comp, a, left - 1);
    quickSortInPlace(S, comp, left + 1, b);
  }
}
