package org.example;

//import org.uma.ed.datastructures.stack.LinkedStack;


import org.utils.range.Range;

/**
 * Simple Stack demo
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class LinkedStackDemo {
  public static void main(String[] args) {
    Stack<Integer> stack1 = LinkedStack.empty();
    stack1.push(1);
    stack1.push(2);
    stack1.push(3);
    stack1.push(4);
    int x = stack1.top();
    System.out.println(x);
    stack1.pop();
    System.out.println(stack1);
    System.out.println();


    Stack<Integer> stack2 = LinkedStack.empty();
    stack2.push(1);
    stack2.push(2);
    stack2.push(3);
    System.out.println(stack2);
    System.out.println();

    Stack<Integer> stack3 = LinkedStack.of(1, 2, 3);
    System.out.println(stack3);
    System.out.println();

    System.out.println(stack1.equals(stack2));
    System.out.println(stack1.equals(stack3));
    System.out.println(stack2.equals(stack3));
    System.out.println();

    Stack<Integer> stack4 = LinkedStack.from(Range.inclusive(0, 10, 2));
    System.out.println(stack4);

    

  }
}
