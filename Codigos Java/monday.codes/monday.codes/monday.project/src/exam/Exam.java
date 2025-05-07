///////////////////////////////////////////////////////////////////////////////
// Student's name: [Your Name]
// Identity number:  [Your DNI or Passport Number] 
///////////////////////////////////////////////////////////////////////////////

package exam;


import datastructures.tree.binary.BT;
import datastructures.tree.general.GT;
import datastructures.bag.SB;
import datastructures.list.List;

import java.util.Comparator;

public class Exam {
  // Bags

  //// BEGIN (A)
  public static <E> SB.Node<E> insert(E element, SB.Node<E> first, Comparator<E> comparator) {

      if (first == null) {
          first = new SB.Node<>(element, 1, null);
          return first;
      }

      SB.Node<E> previous = null;
      SB.Node<E> current = first;

      int cmp = 0;
      while (current != null && (cmp = comparator.compare(element, current.element)) > 0) {
        previous = current;
        current = current.next;
      }
      boolean found = (current != null) && (cmp == 0);

      if (found){
        current.occurrences++;
      } else if (current == first) {
          first = new SB.Node<>(element, 1, first);
      } else {
        previous.next = new SB.Node<>(element, 1, current);
      }

      return first;
  }

  //// END (A)


  // Binary Trees

  //// BEGIN (B)
  public static <E> int height(BT.Node<E> root) {
      if (root == null) {
          return 0;
      }
      int hL = height(root.left);
      int hR = height(root.right);
    return 1 + (Math.max(hL, hR));
  }
  //// END (B)


  //// BEGIN (C)
  public static <E> boolean isBalanced(BT.Node<E> root) {
      if (root == null) {
          return true;
      }
      int difference = height(root.right)-height(root.left);
      return (-1 <= difference && difference <= 1) && isBalanced(root.left) && isBalanced(root.right);
  }
  //// END (C)


  //// BEGIN (D)
  public static <E> BT.Node<E> zigZagInsert(E element, BT.Node<E> root) {
    return root = zigZagInsert(element, root, true);
  }

  private static <E> BT.Node<E> zigZagInsert(E element, BT.Node<E> node, boolean left) {
    if (node == null){
        node = BT.Node.of(element);
    }
    else {
        if (left){
            node.left = zigZagInsert(element, node.left, false);
        }
        else {
            node.right = zigZagInsert(element, node.right, true);
        }
    }
      return node;
  }
  //// END (D)


  // General Trees

  //// BEGIN (E)
  public static boolean isHeap(GT.Node<Integer> root) {
      if (root == null){
          return true;
      }
    return isHeap(root.element, root.children);
  }

  private static boolean isHeap(Integer element, List<GT.Node<Integer>> children) {
      if (children.isEmpty()){
        return true;
      }
      boolean isHeap = true;
      for (GT.Node<Integer> child : children) {
        if (child.element < element) {
            return false;
        }
        isHeap = isHeap(child.element, child.children);
      }
      return isHeap;
  }
  //// END (E)



  public static void main(String[] args) {
    // Run this main method to test your solutions.
    Tests.main(args);
  }
}