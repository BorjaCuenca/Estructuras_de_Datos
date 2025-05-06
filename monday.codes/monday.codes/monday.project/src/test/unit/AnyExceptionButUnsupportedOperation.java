package test.unit;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A test where the expression to be evaluated must throw any exception (except for UnsupportedOperationException)
 * verifying specific message predicate to pass.
 * @param <T> Type of the expression to evaluate.
 * @author Pepe Gallardo
 */
public class AnyExceptionButUnsupportedOperation<T> extends ExceptionBy<T> {
  public AnyExceptionButUnsupportedOperation(String name, Supplier<T> toEvaluate, Predicate<String> messagePredicate, int timeout) {
    super(name, toEvaluate, (Throwable thrown) -> !(thrown instanceof UnsupportedOperationException), messagePredicate, "Exception should not be UnsupportedOperationException", timeout);
  }

  public AnyExceptionButUnsupportedOperation(String name, Supplier<T> toEvaluate, Predicate<String> messagePredicate) {
    this(name, toEvaluate, messagePredicate, defaultTimeout);
  }

  public AnyExceptionButUnsupportedOperation(String name, Supplier<T> toEvaluate, int timeout) {
    this(name, toEvaluate, (String message) -> true, timeout);
  }

  public AnyExceptionButUnsupportedOperation(String name, Supplier<T> toEvaluate) {
    this(name, toEvaluate, (String message) -> true, defaultTimeout);
  }
}