package datastructures.tree.binary;

public class Ops {
  private static String toString(StringBuilder sb, BT.Node<?> node) {
    if (node == null) {
      sb.append("null");
    } else {
      sb.append("BT.Node.of(").append(node.element);
      if (node.left != null || node.right != null) {
        sb.append(", ");
        toString(sb, node.left);
        sb.append(", ");
        toString(sb, node.right);
      }
      sb.append(")");
    }
    return sb.toString();
  }

  public static <E> boolean sameElements(BT.Node<E> node1, BT.Node<?> node2) {
    if (node1 == null) {
      return node2 == null;
    } else if (node2 == null) {
      return false;
    } else {
      return node1.element.equals(node2.element)
          && sameElements(node1.left, node2.left)
          && sameElements(node1.right, node2.right);
    }
  }

  public static String toString(BT.Node<?> node) {
    return toString(new StringBuilder(), node);
  }

  public static <E> void prettyPrint(BT.Node<E> tree) {
    StringBuilder sb = new StringBuilder(50);
    prettyPrint(tree, sb, "", "");
    System.out.println(sb);
  }

  private static <E> void prettyPrint(BT.Node<E> tree, StringBuilder buffer, String prefix, String childrenPrefix) {
    if(tree==null) {
      buffer.append(prefix);
      buffer.append(".\n");
      return;
    }

    buffer.append(prefix);
    buffer.append(tree.element);
    buffer.append('\n');
    prettyPrint(tree.left, buffer, childrenPrefix + "├─ ", childrenPrefix + "│  ");
    prettyPrint(tree.right, buffer, childrenPrefix + "└─ ", childrenPrefix + "   ");
  }
}
