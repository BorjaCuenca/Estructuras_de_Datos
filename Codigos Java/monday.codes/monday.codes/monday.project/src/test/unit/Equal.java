package test.unit;

import java.util.function.Supplier;

/**
 * A test where the expression to be evaluated must be equals to the expected result to pass.
 * @param <T> Type of the expression to evaluate and result.
 * @author Pepe Gallardo
 */
public class Equal<T> extends EqualBy<T> {

  public Equal(String name, Supplier<T> toEvaluate, T expected, int timeout) {
    super(name, Object::equals, toEvaluate, expected, timeout);
  }

  public Equal(String name, Supplier<T> toEvaluate, T expected) {
    this(name, toEvaluate, expected, defaultTimeout);
  }
}
