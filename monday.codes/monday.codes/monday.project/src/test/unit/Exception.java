package test.unit;

import java.util.function.Supplier;

/**
 * A test where the expression to be evaluated must throw an exception with a specific message to pass.
 * @param <T> Type of the expression to evaluate.
 * @author Pepe Gallardo
 */
public class Exception<T> extends ExceptionBy<T> {
  public Exception(String name, Supplier<T> toEvaluate, Class<? extends Throwable> expectedException,
                   String expectedMessage,
                   int timeout) {
    super(name, toEvaluate,
        expectedException::isInstance,
        (String message) -> message.equals(expectedMessage),
        "%s with message \"%s\" was expected".formatted(expectedException.getSimpleName(), expectedMessage),
        timeout);
  }

  public Exception(String name, Supplier<T> toEvaluate, Class<? extends Throwable> expectedException, String message) {
    this(name, toEvaluate, expectedException, message, defaultTimeout);
  }

  public Exception(String name, Supplier<T> toEvaluate, String message) {
    this(name, toEvaluate, RuntimeException.class, message, defaultTimeout);
  }

  public Exception(String name, Supplier<T> toEvaluate, Class<? extends Throwable> expectedException, int timeout) {
    super(name, toEvaluate,
        expectedException::isInstance,
        (String message) -> true,
        "%s was expected".formatted(expectedException.getSimpleName()),
        timeout);
  }

  public Exception(String name, Supplier<T> toEvaluate, Class<? extends Throwable> expectedException) {
    this(name, toEvaluate, expectedException, defaultTimeout);
  }

  public Exception(String name, Supplier<T> toEvaluate, int timeout) {
    this(name, toEvaluate, RuntimeException.class, timeout);
  }

  public Exception(String name, Supplier<T> toEvaluate) {
    this(name, toEvaluate, RuntimeException.class, defaultTimeout);
  }
}
