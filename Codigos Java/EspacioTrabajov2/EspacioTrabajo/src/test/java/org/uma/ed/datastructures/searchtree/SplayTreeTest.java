package org.uma.ed.datastructures.searchtree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;


public class SplayTreeTest {
        private SplayTree<Integer> tree;

        @BeforeEach
        public void setUp() {
            tree = SplayTree.empty();
        }

        @Test
        public void testInsert() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(5);

            assertEquals(3,tree.size());

            Iterable<Integer> inOrder = tree.inOrder();
            Integer[] expected = {5, 10, 20};
            assertArrayEquals(expected, toArray(inOrder));

        }

        @Test
        public void testSearch() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(5);

            assertEquals(10, tree.search(10));
            assertEquals(20, tree.search(20));
            assertEquals(5, tree.search(5));
            assertNull(tree.search(15));
        }

        @Test
        public void testDelete() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(5);

            tree.delete(10);
            Iterable<Integer> inOrder = tree.inOrder();
            Integer[] expected = {5, 20};
            assertArrayEquals(expected, toArray(inOrder));

            tree.delete(5);
            Integer[] expected1 = {5, 20};
            assertArrayEquals(expected1, toArray(inOrder));

            tree.delete(20);
            assertTrue(tree.isEmpty());
        }

        @Test
        public void testMinimum() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(5);

            assertEquals(5, tree.minimum());
        }


        @Test
        public void testDeleteMinimum() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(5);

            tree.deleteMinimum();
            assertFalse(tree.contains(5));
            assertEquals(10, tree.minimum());
        }


        @Test
        public void testClear() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(5);

            tree.clear();
            assertTrue(tree.isEmpty());
        }

        @Test
        public void testSize() {
            assertEquals(0, tree.size());

            tree.insert(10);
            assertEquals(1, tree.size());

            tree.insert(20);
            assertEquals(2, tree.size());

            tree.delete(10);
            assertEquals(1, tree.size());

            tree.clear();
            assertEquals(0, tree.size());
        }

        @Test
        public void testHeight() {
            assertEquals(0, tree.height());

            tree.insert(10);
            assertEquals(1, tree.height());

            tree.insert(20);
            assertEquals(2, tree.height());

            tree.insert(5);
            System.out.println(tree.toString());

            tree.insert(10);
            assertEquals(2, tree.height());
        }

        @Test
        public void testInOrder() {
            tree.insert(10);
            tree.insert(20);
            tree.insert(5);

            Iterable<Integer> inOrder = tree.inOrder();
            Integer[] expected = {5, 10, 20};
            assertArrayEquals(expected, toArray(inOrder));
        }

    @Test
    public void testCopyOfSplay() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        Iterable<Integer> inOrder1 = tree.inOrder();
        Iterable<Integer> inOrder2 = SplayTree.copyOf(tree).inOrder();

        assertArrayEquals(toArray(inOrder2), toArray(inOrder1));
    }

    @Test
    public void testCopyOfSearchTree() {
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        Iterable<Integer> inOrder1 = tree.inOrder();
        Iterable<Integer> inOrder2 = SplayTree.copyOf((SearchTree<Integer>)tree).inOrder();

        assertArrayEquals(toArray(inOrder2), toArray(inOrder1));
    }



        private Integer[] toArray(Iterable<Integer> iterable) {
            ArrayList<Integer> list = new ArrayList<>();
            for (Integer item : iterable) {
                list.add(item);
            }
            return list.toArray(new Integer[0]);
        }
}

