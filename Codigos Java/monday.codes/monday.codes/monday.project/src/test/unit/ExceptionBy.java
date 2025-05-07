package test.unit;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * A test where the expression to be evaluated must throw an exception verifying specific predicates to pass.
 * @param <T> Type of the expression to evaluate.
 * @author Pepe Gallardo
 */
public class ExceptionBy<T> extends Test {
    private final Supplier<T> toEvaluate;
    private final Predicate<Throwable> throwablePredicate;
    private final Predicate<String> messagePredicate;
    private final String help;

    public ExceptionBy(String name, Supplier<T> toEvaluate, Predicate<Throwable> throwablePredicate, Predicate<String> messagePredicate, String help, int timeout) {
        this.name = name;
        this.toEvaluate = toEvaluate;
        this.throwablePredicate = throwablePredicate;
        this.messagePredicate = messagePredicate;
        this.help = help;
        this.timeout = timeout;
    }

    public ExceptionBy(String name, Supplier<T> toEvaluate, Predicate<Throwable> throwablePredicate, Predicate<String> messagePredicate, String help) {
        this(name, toEvaluate, throwablePredicate, messagePredicate, help, defaultTimeout);
    }

    public ExceptionBy(String name, Supplier<T> toEvaluate, Predicate<String> messagePredicate, String help) {
        this(name, toEvaluate, (Throwable thrown) -> thrown instanceof RuntimeException, messagePredicate, help, defaultTimeout);
    }

    public ExceptionBy(String name, Supplier<T> toEvaluate, Predicate<Throwable> throwablePredicate, Predicate<String> messagePredicate) {
        this(name, toEvaluate, throwablePredicate, messagePredicate, null, defaultTimeout);
    }

    public ExceptionBy(String name, Supplier<T> toEvaluate, Predicate<String> messagePredicate) {
        this(name, toEvaluate, (Throwable thrown) -> thrown instanceof RuntimeException, messagePredicate, null, defaultTimeout);
    }

    private String helpIfPresent() {
        return help == null ? "" : ". %s".formatted(help);
    }

    // returns true if the expression throws the expected exception
    private boolean test() {
        try {
            T result = toEvaluate.get();
            // no exception was thrown
            if (logging) {
                System.out.printf("""
            FAILED:
              Test should throw an exception but it did not%s
              Obtained result was %s
            """, helpIfPresent(), result);
            }
            return false;
        } catch (Throwable thrown) {
            // exceptions must be caught here and not in the future.get block
            boolean passedThrowable = throwablePredicate.test(thrown);
            boolean passedMessage = messagePredicate.test(thrown.getMessage());
            boolean passed = passedThrowable && passedMessage;
            if (logging) {
                if (passed) {
                    // expected exception was thrown
                    System.out.print("OK\n");
                } else if (!passedThrowable && !passedMessage) {
                    System.out.printf("""
                            FAILED!
                              Test threw exception %s with message "%s"
                              But another exception type and another message was expected%s
                            """, thrown.getClass().getSimpleName(), thrown.getMessage(), helpIfPresent());
                } else if (!passedThrowable) {
                    System.out.printf("""
                            FAILED!
                              Test threw exception %s
                              But another exception type was expected%s
                            """, thrown.getClass().getSimpleName(), helpIfPresent());
                } else {
                    System.out.printf("""
                            FAILED!
                              Test threw expected exception %s with message "%s"
                              But another message was expected%s
                            """, thrown.getClass().getSimpleName(), thrown.getMessage(), helpIfPresent());
                }
            }
            return passed;
        }
    }


    public boolean run() {
        super.run();

        // Create a CompletableFuture to run the test() method asynchronously
        final CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(this::test);

        try {
            // Wait for the future to complete, with a specified timeout
            return future.get(timeout, TimeUnit.SECONDS);
        } catch (final TimeoutException e) {
            // calculation takes too long
            if (logging) {
                System.out.printf("""
                    FAILED!
                      Test should throw an exception but it did not
                      Timeout: test took more than %d seconds to complete
                    """, timeout);
            }
            return false;
        } catch (Throwable thrown) {
            // catch any other exceptions thrown by the future.get() method
            if (logging) {
                System.out.printf("""
                    FAILED!
                      Test threw this exception: %s
                      But another exception type was expected%s
                    """, thrown.getClass().getSimpleName(), helpIfPresent());
            }
            return false;
        } finally {
            if (logging) {
                System.out.flush();
            }
        }
    }
}
