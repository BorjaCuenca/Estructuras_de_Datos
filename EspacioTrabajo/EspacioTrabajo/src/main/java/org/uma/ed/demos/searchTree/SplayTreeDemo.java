package org.uma.ed.demos.searchTree;

import org.uma.ed.datastructures.searchtree.SearchTree;
import org.uma.ed.datastructures.searchtree.SplayTree;

/**
 * Simple SearchTree demo.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class SplayTreeDemo {

  public static void main(String[] args) {
    SearchTree<Integer> t = SplayTree.empty();

    int[] array = new int[]{8, 5, 7, 1, 9, 6};
    t.insert(8);
    //       8
    System.out.println(t);

    t.insert(5);
    //       5
    //          8
    System.out.println(t);

    t.insert(7);
    //       7
    //   5       8
    System.out.println(t);

    t.insert(1);
    //       1
    //    x     5
    //       x     7
    //          x    8
    System.out.println(t);

    t.insert(9);
    //          9
    //      5        x
    //  1       8
    //        7   x
    //
    System.out.println(t);

    t.insert(6);
    //         6
    //    5        9
    //  1   x    7    x
    //          x  8
    System.out.println(t);

    System.out.println("Minim " + t.minimum());
    System.out.println("Arbol:");
    for(var i: t.inOrder()){
      System.out.print(i +" ");
    }
    System.out.println("\nBorramos el " + t.minimum());
    t.deleteMinimum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Arbol:");
    for(var i: t.inOrder()){
      System.out.print(i +" ");
    }
    System.out.println("\nBorramos el " + t.minimum());
    t.deleteMinimum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Arbol:");
    for(var i: t.inOrder()){
      System.out.print(i +" ");
    }
    System.out.println("\nBorramos el " + t.minimum());
    t.deleteMinimum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Arbol:");
    for(var i: t.inOrder()){
      System.out.print(i +" ");
    }
    System.out.println("\nBorramos el " + t.minimum());
    t.deleteMinimum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Arbol:");
    for(var i: t.inOrder()){
      System.out.print(i +" ");
    }
    System.out.println("\nBorramos el " + t.minimum());
    t.deleteMinimum();

    System.out.println("Minim " + t.minimum());
    System.out.println("Arbol:");
    for(var i: t.inOrder()){
      System.out.print(i +" ");
    }

  }
}