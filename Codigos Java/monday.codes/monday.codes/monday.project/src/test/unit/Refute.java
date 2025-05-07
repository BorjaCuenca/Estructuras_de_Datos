package test.unit;

import java.util.function.Supplier;

/**
 * A test where the expression to be evaluated must be false for the test to pass.
 * @author Pepe Gallardo
 */
public class Refute extends Property<Boolean> {
  public Refute(String name, Supplier<Boolean> toEvaluate, int timeout) {
    super(name, toEvaluate, (x) -> !x, "should be false", timeout);
  }

  public Refute(String name, Supplier<Boolean> toEvaluate) {
    this(name, toEvaluate, defaultTimeout);
  }
}
