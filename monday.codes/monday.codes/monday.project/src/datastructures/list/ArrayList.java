package datastructures.list;

import java.util.Collections;
import java.util.Iterator;
import java.util.StringJoiner;

/**
 * ArrayList is a class that implements a List interface using {@link ArrayList}.
 *
 * @param <T> The type of elements in list.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class ArrayList<T> implements List<T> {
  /**
   * Default initial capacity for list.
   */
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  /**
   * JDK ArrayList of elements.
   */
  private final java.util.ArrayList<T> list;

  /**
   * Constructs an ArrayList with given internal representation.
   *
   * @param list JDK's ArrayList to be used as internal representation.
   */
  private ArrayList(java.util.ArrayList<T> list) {
    this.list = list;
  }

  /**
   * Constructs an empty ArrayList with the specified initial capacity.
   * The capacity is the size of the array used to store the elements in the list. It is always at least as large as the
   * list size. As elements are added to an ArrayList, its capacity grows automatically.
   * <p> Time complexity: O(1)
   *
   * @param initialCapacity the initial capacity of the list
   * @throws IllegalArgumentException if the specified initial capacity is negative
   */
  public ArrayList(int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("initial capacity must be greater than 0");
    }
    list = new java.util.ArrayList<>(initialCapacity);
  }

  /**
   * Constructs an empty ArrayList with default initial capacity.
   * The capacity is the size of the array used to store the elements in the list. It is always at least as large as the
   * list size. As elements are added to an ArrayList, its capacity grows automatically.
   * <p> Time complexity: O(1)
   *
   */
  public ArrayList() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * Constructs an empty ArrayList with default initial capacity.
   * The capacity is the size of the array used to store the elements in the list. It is always at least as large as the
   * list size. As elements are added to an ArrayList, its capacity grows automatically.
   * <p> Time complexity: O(1)
   *
   * @param <T> Type of elements in list.
   *
   * @return an empty ArrayList.
   */
  public static <T> ArrayList<T> empty() {
    return new ArrayList<>();
  }

  /**
   * Constructs an empty ArrayList with the specified initial capacity.
   * The capacity is the size of the array used to store the elements in the list. It is always at least as large as the
   * list size. As elements are added to an ArrayList, its capacity grows automatically.
   * <p> Time complexity: O(1)
   *
   * @param initialCapacity the initial capacity of the list
   * @param <T> Type of elements in list.
   * @throws IllegalArgumentException if the specified initial capacity is negative
   *
   * @return an empty ArrayList with given capacity.
   */
  public static <T> ArrayList<T> withCapacity(int initialCapacity) {
    return new ArrayList<>(initialCapacity);
  }

  /**
   * Returns a new ArrayList with given elements.
   * <p> Time complexity: O(n)
   *
   * @param elements Elements to append to new list.
   * @param <T> Type of elements in set.
   *
   * @return a new ArrayList with given elements.
   */
  @SafeVarargs
  public static <T> ArrayList<T> of(T... elements) {
    java.util.ArrayList<T> list = new java.util.ArrayList<>(elements.length);
    Collections.addAll(list, elements);
    return new ArrayList<>(list);
  }

  /**
   * Creates an ArrayList with elements in given iterable.
   * <p> Time complexity: O(n)
   *
   * @param iterable {@code Iterable} of elements to be added to list.
   * @param <T> Type of elements in iterable.
   *
   * @return an ArrayList with elements in given iterable.
   */
  public static <T> ArrayList<T> from(Iterable<T> iterable) {
    int size = 0;
    for (var _ : iterable) {
      size++;
    }
    java.util.ArrayList<T> list = new java.util.ArrayList<>(size);
    for (T element : iterable) {
      list.add(element);
    }
    return new ArrayList<>(list);
  }

  /**
   * Returns a new ArrayList with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that ArrayList to be copied.
   * @param <T> Type of elements in set.
   *
   * @return a new ArrayList with same elements and order as {@code that}.
   */
  public static <T> ArrayList<T> copyOf(ArrayList<T> that) {
    java.util.ArrayList<T> list = new java.util.ArrayList<>(that.list);
    return new ArrayList<>(list);
  }

  /**
   * Returns a new ArrayList with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that List to be copied.
   * @param <T> Type of elements in set.
   *
   * @return a new ArrayList with same elements and order as {@code that}.
   */
  public static <T> ArrayList<T> copyOf(List<T> that) {
    if (that instanceof ArrayList<T> arrayList) {
      // use specialized version for ArrayList
      return copyOf(arrayList);
    }
    java.util.ArrayList<T> list = new java.util.ArrayList<>(that.size());
    for (T element : that) {
      list.add(element);
    }
    return new ArrayList<>(list);
  }

  /**
   * Removes all elements from list
   * <p> Time complexity: O(n)
   */
  @Override
  public void clear() {
    list.clear();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return list.size();
  }

  /**
   * Checks if the given index is in the range of valid indices for the ArrayList.
   * @param index the index to check
   * @throws ListException if the index is out of range (index < 0 || index >= size())
   */
  private void validateIndex(int index) {
    if (index < 0 || index >= size()) {
      throw new ListException("Invalid index " + index);
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   *
   * @throws ListException {@inheritDoc}
   */
  @Override
  public void insert(int index, T element) {
    list.add(index, element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   *
   * @throws ListException {@inheritDoc}
   */
  @Override
  public void delete(int index) {
    validateIndex(index);
    list.remove(index);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws ListException {@inheritDoc}
   */
  @Override
  public T get(int index) {
    validateIndex(index);
    return list.get(index);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws ListException {@inheritDoc}
   */
  @Override
  public void set(int index, T element) {
    validateIndex(index);
    list.set(index, element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: mostly O(1). O(n) when list capacity has to be increased.
   */
  @Override
  public void append(T element) {
    list.add(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public void prepend(T element) {
    list.addFirst(element);
  }

  /**
   * Returns an iterator over elements in this ArrayList. Notice that, if the list is structurally modified in any way
   * while the iterator is being used, the iterator state will become inconsistent and will not behave as expected.
   *
   * @see Iterable#iterator()
   *
   * @return an iterator over the elements in this list from first to last.
   */
  @Override
  public Iterator<T> iterator() {
    return list.iterator();
  }

  @Override
  public boolean equals(Object o) {
    return list.equals(o);
  }

  @Override
  public int hashCode() {
    return list.hashCode();
  }

  @Override
  public String toString() {
    StringJoiner list = new StringJoiner(", ", "ArrayList(", ")");
    for (T element : this) {
      list.add(element.toString());
    }
    return list.toString();
  }
}