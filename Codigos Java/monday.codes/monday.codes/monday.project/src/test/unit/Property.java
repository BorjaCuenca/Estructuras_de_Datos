package test.unit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A test where the expression to be evaluated must verify a property for the test to pass.
 * @param <T> Type of the expression to evaluate.
 * @author Pepe Gallardo
 */
public class Property<T> extends Test {
  private final Supplier<T> toEvaluate;
  private final Predicate<T> property;
  private final String help;
  private T result;

  public Property(String name, Supplier<T> toEvaluate, Predicate<T> property, String help, int timeout) {
    this.name = name;
    this.toEvaluate = toEvaluate;
    this.property = property;
    this.help = help;
    this.timeout = timeout;
  }

  public Property(String name, Supplier<T> toEvaluate, Predicate<T> property, String help) {
    this(name, toEvaluate, property, help, defaultTimeout);
  }

  public Property(String name, Supplier<T> toEvaluate, Predicate<T> property) {
    this(name, toEvaluate, property, null, defaultTimeout);
  }

  private boolean test() {
    result = toEvaluate.get();
    return property.test(result);
  }

  private String helpIfPresent() {
    return help == null ? "" : ": %s".formatted(help);
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
                Does not verify expected property%s
                Obtained result was %s
              """, helpIfPresent(), result);
        }
      }
      return passed;
    } catch (final TimeoutException e) {
      // calculation takes too long
      if (logging) {
        System.out.printf("""
            FAILED!
              Does not verify expected property%s
              Timeout: test took more than %d seconds to complete
            """, helpIfPresent(), timeout);
      }
      return false;
    } catch (final java.lang.Exception thrown) {
      // catch any other exceptions thrown by the test() method
      if (logging) {
        System.out.printf("""
            FAILED!
              Does not verify expected property%s
              Raised exception %s with message "%s"
            """, helpIfPresent(), thrown.getClass().getSimpleName(), thrown.getMessage());
      }
      return false;
    } finally {
      if (logging) {
        System.out.flush();
      }
    }
  }
}
