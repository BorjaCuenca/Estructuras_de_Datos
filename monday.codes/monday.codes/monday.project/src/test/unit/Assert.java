package test.unit;

import java.util.function.Supplier;

/**
 * A test where the expression to be evaluated must be true for the test to pass.
 * @author Pepe Gallardo, 2024
 */
public class Assert extends Property<Boolean> {
  public Assert(String name, Supplier<Boolean> toEvaluate, int timeout) {
    super(name, toEvaluate, (x) -> x, "should be true", timeout);
  }

  public Assert(String name, Supplier<Boolean> toEvaluate) {
    this(name, toEvaluate, defaultTimeout);
  }
}
