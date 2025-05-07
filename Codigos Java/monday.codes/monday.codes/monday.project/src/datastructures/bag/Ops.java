package datastructures.bag;

public class Ops {
  public static <E> boolean sameElements(SB.Node<E> node1, SB.Node<?> node2) {
      while (node1 != null && node2 != null) {
        if (node1.occurrences != node2.occurrences || !node1.element.equals(node2.element)) {
          return false;
        }
        node1 = node1.next;
        node2 = node2.next;
      }
      return node1 == null && node2 == null;
    }

  public static String toString(SB.Node<?> node) {
    StringBuilder sb = new StringBuilder();
    StringBuilder suffix = new StringBuilder();
    while (node != null) {
      if (node.occurrences < 1) {
        throw new IllegalStateException("Invalid number of occurrences: " + node.occurrences);
      } else {
        sb.append(String.format("SB.Node.of(%s, %d, ", node.element.toString(), node.occurrences));
        suffix.append(")");
      }
      node = node.next;
    }
    return sb.append("null").append(suffix).toString();
  }
}
