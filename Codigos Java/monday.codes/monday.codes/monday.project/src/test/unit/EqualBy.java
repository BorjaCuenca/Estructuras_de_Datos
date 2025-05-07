package test.unit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A test where the expression to be evaluated must be equals the expected result to pass, according to a
 * specified comparison function.
 * @param <T> Type of the expression to evaluate and result.
 * @author Pepe Gallardo
 */
public class EqualBy<T> extends Test {
  private final BiPredicate<T, T> equals;
  private final Supplier<T> toEvaluate;
  private final T expected;
  private final Function<T, String> toString;
  private T result;

  public EqualBy(String name, BiPredicate<T, T> equals, Supplier<T> toEvaluate, T expected,
                 Function<T, String> toString, int timeout) {
    this.name = name;
    this.equals = equals;
    this.toEvaluate = toEvaluate;
    this.expected = expected;
    this.toString = toString;
    this.timeout = timeout;
  }

  public EqualBy(String name, BiPredicate<T, T> equals, Supplier<T> toEvaluate, T expected, Function<T, String> toString) {
    this(name, equals, toEvaluate, expected, toString, defaultTimeout);
  }

  public EqualBy(String name, BiPredicate<T, T> equals, Supplier<T> toEvaluate, T expected, int timeout) {
    this(name, equals, toEvaluate, expected, Object::toString, timeout);
  }

  public EqualBy(String name, BiPredicate<T, T> equals, Supplier<T> toEvaluate, T expected) {
    this(name, equals, toEvaluate, expected, Object::toString, defaultTimeout);
  }

  private boolean test() {
    result = toEvaluate.get();
    return equals.test(result, expected);
  }

  public boolean run() {
    super.run();

    // Create a CompletableFuture to run the test() method asynchronously
    final CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(this::test);

    try {
      // Wait for the future to complete, with a specified timeout
      boolean passed = future.get(timeout, TimeUnit.SECONDS);
      if (logging) {
        if (passed) {
          System.out.print("OK\n");
        } else {
          System.out.printf("""
              FAILED!
                Expected result was %s
                Obtained result was %s
              """, toString.apply(expected), toString.apply(result));
        }
      }
      return passed;
    } catch (final TimeoutException e) {
      // calculation takes too long
      if (logging) {
        System.out.printf("""
            FAILED!
              Expected result was %s
              Timeout: test took more than %d seconds to complete
            """, toString.apply(expected), timeout);
      }
      return false;
    } catch (final java.lang.Exception e) {
      // catch any other exceptions thrown by the test() method
      if (logging) {
        System.out.printf("""
            FAILED!
              Expected result was %s
              Raised an exception: %s
            """, toString.apply(expected), e.getMessage());
      }
      return false;
    } finally {
      if (logging) {
        System.out.flush();
      }
    }
  }
}
