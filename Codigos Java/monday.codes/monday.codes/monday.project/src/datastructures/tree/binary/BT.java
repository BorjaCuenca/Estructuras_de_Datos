package datastructures.tree.binary;

public class BT {
  public static final class Node<E> {
    public E element;            // the element in the node
    public Node<E> left, right;  // the left and right children (or null if they don't exist)

    /**
     * Creates a node with an element and no children.
     *
     * @param element Element in node.
     * @param left The left child node (or null if it doesn't exist).
     * @param right The right child node (or null if it doesn't exist).
     */
    Node(E element, Node<E> left, Node<E> right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    /**
     * Creates a node with an element and no children.
     *
     * @param element Element in node.
     * @param left The left child node (or null if it doesn't exist).
     * @param right The right child node (or null if it doesn't exist).
     * @param <E> The type of the element in the node.
     *
     * @return A new node with given element and children.
     */
    public static <E> Node<E> of(E element, Node<E> left, Node<E> right) {
      return new Node<>(element, left, right);
    }

    /**
     * Creates a node with an element and no children.
     * @param element Element in node.
     * @param <E> The type of the element in the node.
     *
     * @return A new node with given element and children.
     */
    public static <E> Node<E> of(E element) {
      return new Node<>(element, null, null);
    }
  }
}
