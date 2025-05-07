package datastructures.tree.general;


import java.util.Iterator;

public class Ops {
  public static <E> String serialize(GT.Node<E> tree) {
    StringBuilder sb = new StringBuilder();
    serialize(tree, sb);
    return sb.toString();
  }

  private static <E> void serialize(GT.Node<E> tree, StringBuilder sb) {
    if (tree == null) {
      return;
    }
    sb.append(tree.element);
    sb.append(",");
    for (GT.Node<E> child : tree.children) {
      serialize(child, sb);
    }
    sb.append("#,");
  }

  public interface Parser<E> {
    E parse(String str);
  }

  public static final Parser<Integer> integerParser = Integer::parseInt;

  public static <E> GT.Node<E> deserialize(String str, Parser<E> parser) {
    if(str.isEmpty()) {
      return null;
    }
    String[] parts = str.split(",");
    int[] index = new int[] {0};
    return deserialize(parts, index, parser);
  }

  private static <E> GT.Node<E> deserialize(String[] parts, int[] index, Parser<E> parser) {
    if (index[0] >= parts.length) {
      return null;
    }
    String part = parts[index[0]];
    index[0]++;
    if (part.equals("#")) {
      return null;
    }
    GT.Node<E> root = new GT.Node<>(parser.parse(part));
    while (true) {
      GT.Node<E> child = deserialize(parts, index, parser);
      if (child == null) {
        break;
      }
      root.children.append(child);
    }
    return root;
  }

  public static <E> void prettyPrint(GT.Node<E> tree) {
    StringBuilder sb = new StringBuilder(50);
    prettyPrint(tree, sb, "", "");
    System.out.println(sb);
  }

  private static <E> void prettyPrint(GT.Node<E> tree, StringBuilder buffer, String prefix, String childrenPrefix) {
    if(tree==null) {
      buffer.append(".\n");
      return;
    }

    buffer.append(prefix);
    buffer.append(tree.element);
    buffer.append('\n');
    for (Iterator<GT.Node<E>> it = tree.children.iterator(); it.hasNext();) {
      GT.Node<E> child = it.next();
      if (it.hasNext()) {
        prettyPrint(child, buffer, childrenPrefix + "├─ ", childrenPrefix + "│  ");
      } else {
        prettyPrint(child, buffer, childrenPrefix + "└─ ", childrenPrefix + "   ");
      }
    }
  }

  public static <E> boolean sameElements(GT.Node<E> node1, GT.Node<?> node2) {
    if (node1 == null) {
      return node2 == null;
    } else if (node2 == null) {
      return false;
    } else if (!node1.element.equals(node2.element)) {
      return false;
    } else if (node1.children.size() != node2.children.size()) {
      return false;
    } else {
      var it1 = node1.children.iterator();
      var it2 = node2.children.iterator();
      while (it1.hasNext()) {
        if (!sameElements(it1.next(), it2.next())) {
          return false;
        }
      }
      return true;
    }
  }
}
