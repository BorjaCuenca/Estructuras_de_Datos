package datastructures.tree.general;

import datastructures.list.ArrayList;
import datastructures.list.List;

public class GT {
  public static final class Node<E> {
    public E element;                    // the element in the node
    public final List<Node<E>> children; // the list of children nodes

    /**
     * Creates a node with an element and no children.
     *
     * @param element Element in node.
     */
    public Node(E element) {
      this.element = element;
      this.children = ArrayList.empty();
    }

    /**
     * Creates a node with an element and a list of children.
     *
     * @param element The element in the node.
     * @param children The list of children nodes.
     * @param <E> The type of the element in the node.
     *
     * @return A new node with given element and children.
     */
    @SafeVarargs
    public static <E> Node<E> of(E element, Node<E>... children) {
      Node<E> node = new Node<>(element);
      for (Node<E> child : children) {
        node.children.append(child);
      }
      return node;
    }
  }
}
