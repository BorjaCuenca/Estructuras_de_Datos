package org.uma.ed.datastructures.searchtree;

import java.util.ArrayList;
import java.util.Comparator;

public class SplayTree<K> implements SearchTree<K> {
    private static final class Node<K> {
        K key;
        Node<K> left, right;

        Node(K key) {
            this.key = key;
        }
    }

    private Node<K> root;
    private final Comparator<K> comparator;
    private int size;

    public SplayTree(Comparator<K> comparator) {
        this(comparator, null, 0);
    }

    private SplayTree(Comparator<K> comparator, Node<K> root, int size) {
        this.comparator = comparator;
        this.root = root;
        this.size = size;
    }

    public static <K extends Comparable<? super K>> SplayTree<K> empty() {
        return new SplayTree<K>(Comparator.naturalOrder());
    }

    public static <K> SplayTree<K> empty(Comparator<K> comparator) {
        return new SplayTree<>(comparator);
    }

    public static <K> SplayTree<K> copyOf(SearchTree<K> that) {
        throw new UnsupportedOperationException("Implementa!");
    }

    /**
     * Returns a new unbalanced binary search tree with same elements and same structure as argument.
     * <p> Time complexity: O(n)
     *
     * @param that binary search tree to be copied.
     *
     * @return a new BST with same elements and structure as {@code that}.
     */
    public static <K> SplayTree<K> copyOf(SplayTree<K> that) {
        throw new UnsupportedOperationException("Implementa!");
    }

    @Override
    public Comparator<K> comparator() {
        return comparator;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node<K> node) {
        return (node == null? 0: 1 + Math.max(height(node.left), height(node.right)));
    }

    private Node<K> rotateRight(Node<K> node) {
        Node<K> x = node.left;
        node.left = x.right;
        x.right = node;
        return x;
    }

    private Node<K> rotateLeft(Node<K> node) {
        Node<K> x = node.right;
        node.right = x.left;
        x.left = node;
        return x;
    }

    private Node<K> zigLeft(Node<K> node){
        return rotateLeft(node);
    }

    private Node<K> zigRight(Node<K> node){
        return  rotateRight(node);
    }

    private Node<K> zigzigRight(Node<K> node){
        Node<K> result = zigRight(node);
        if (result.left != null) {
            result = zigRight(result);
        }
        return result;
    }

    private Node<K> zigzigLeft(Node<K> node){
        Node<K> result = zigLeft(node);
        if (result.right != null) {
            result = zigLeft(result);
        }
        return result;
    }

    private Node<K> zigzagRightLeft(Node<K> node){
        if (node.right.left != null) {
            node.right = zigRight(node.right);
        }
        return zigLeft(node);
    }

    private Node<K> zigzagLeftRight(Node<K> node){
        if (node.left.right != null) {
            node.left = zigLeft(node.left);
        }
        return zigRight(node);
    }

    // Helper methods
    private Node<K> splay(Node<K> node, K key) {
        if (node == null) return null;

        int cmp1 = compare(key, node.key);

        //Si el nodo está en la raiz de este subarbol, se devuelve la raiz. O si no existe el nodo, devolvemos el más cercano.
        if (cmp1==0 || (cmp1 <0 && node.left == null) || (cmp1 > 0 && node.right == null)) return node;

        if (cmp1 < 0) {
            int cmp2 = compare(key, node.left.key);
            if (cmp2 < 0) { // Zig-Zig
                node.left.left = splay(node.left.left, key);
                node = zigzigRight(node);
            } else if (cmp2 > 0) { // Zig-Zag
                node.left.right = splay(node.left.right, key);
                node = zigzagLeftRight(node);
            }
            else{
                node = zigRight(node);
            }
        } else if (cmp1 > 0) {
            int cmp2 = compare(key, node.right.key);
            if (cmp2 > 0) { //  Zig-Zig
                node.right.right = splay(node.right.right, key);
                node = zigzigLeft(node);
            } else if (cmp2 < 0) { //  Zig-Zag
                node.right.left = splay(node.right.left, key);
                node = zigzagRightLeft(node);
            }
            else{
                node = zigLeft(node);
            }
        }
        return node;

    }
    @Override
    //Return EmptySearchTreeException si la clave es null.
    public void insert(K key) {
        if (key == null) {
            throw new EmptySearchTreeException("Cannot insert a NULL key.");
        }
        if (isEmpty()) {
            root = new Node<>(key);
            return;
        }
        root = splay(root, key);
        int cmp = comparator.compare(key, root.key);

        if (cmp<0) { //Insertar a la izquierda
            insertLeft(root, key);
            size++;
        }
        else if (cmp>0) { //Insertar a la derecha
            insertRight(root, key);
            size++;
        }
        else {
            root.key = key;
        }
    }

    private void insertLeft (Node<K> root, K key) {
        Node<K> newNode = new Node<>(key);
        int cmp = comparator.compare(root.left.key, key);

        if (cmp<0) { //Insertar a la izquierda
            newNode.left = root.left;
            root.left = newNode;
        }
        if (cmp>0) { //Insertar a la derecha
            newNode.right = root.left;
            root.left = newNode;
        }
    }
    private void insertRight (Node<K> root, K key) {
        Node<K> newNode = new Node<>(key);
        int cmp = comparator.compare(root.right.key, key);

        if (cmp<0) { //Insertar a la izquierda
            newNode.left = root.right;
            root.right = newNode;
        }
        if (cmp>0) { //Insertar a la derecha
            newNode.right = root.right;
            root.right = newNode;
        }
    }

    @Override
    public K search(K key) {
        if (key == null) {
            throw new EmptySearchTreeException("Cannot search a NULL key.");
        }
        root = splay(root, key);

        int cmp = comparator.compare(root.key, key);
        if (cmp == 0) {
            return root.key;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean contains(K key) {
        return search(key) != null;
    }

    @Override
    public void delete(K key) {
        throw new UnsupportedOperationException("Implementa!");
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public K minimum() {
        if (isEmpty()) throw new EmptySearchTreeException("Tree is empty");
        Node<K> min = root;
        while (min.left != null) min = min.left;
        return min.key;
    }

    @Override
    public void deleteMinimum() {
        delete(minimum());
    }

    @Override
    public Iterable<K> inOrder() {
        ArrayList<K> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(Node<K> node, ArrayList<K> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.key);
        inOrder(node.right, result);
    }



    private int compare(K key1, K key2) {
        return comparator == null ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
    }

    @Override
    public String toString() {
        String className = getClass().getSimpleName();
        StringBuilder sb = new StringBuilder(className).append("(");
        toString(sb, root);
        sb.append(")");

        return sb.toString();
    }

    private static void toString(StringBuilder sb, Node<?> node) {
        if (node == null) {
            sb.append("null");
        } else {
            String className = node.getClass().getSimpleName();
            sb.append(className).append("(");
            toString(sb, node.left);
            sb.append(", ");
            sb.append(node.key);
            sb.append(", ");
            toString(sb, node.right);
            sb.append(")");
        }
    }
}
