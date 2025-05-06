package datastructures.bag;

public class SB {
  public static final class Node<E> {
    public E element;          // the element in the node
    public int occurrences;    // the number of occurrences of the element
    public Node<E> next;       // the next node in the bag

    /**
     * Creates a node with an element, its number of occurrences, and a reference to the next node.
     *
     * @param element Element in node.
     * @param occurrences Number of occurrences of the element.
     * @param next The next node in the bag.
     *
     */
    public Node(E element, int occurrences, Node<E> next) {
      this.element = element;
      this.occurrences = occurrences;
      this.next = next;
    }

    public static <E> Node<E> of(E element, int occurrences, Node<E> next) {
      return new Node<>(element, occurrences, next);
    }
  }
}
