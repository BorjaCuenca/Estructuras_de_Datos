package test.unit;

import java.util.StringJoiner;

/**
 * Results of running a suite of tests.
 * @author Pepe Gallardo
 */
public class Results {
  private final boolean[] results;

  public Results(boolean[] results) {
    this.results = results;
  }

  public int getPassed() {
    int passed = 0;
    for (boolean result : results) {
      if (result) {
        passed++;
      }
    }
    return passed;
  }

  public int getFailed() {
    int failed = 0;
    for (boolean result : results) {
      if (!result) {
        failed++;
      }
    }
    return failed;
  }

  public int getTotal() {
    return results.length;
  }

  public boolean[] getResults() {
    return results;
  }

  public String getDetails() {
    StringJoiner sj = new StringJoiner("");
    for (boolean result : results) {
      sj.add(result ? "+" : "-");
    }
    return sj.toString();
  }

  public boolean isSuccessful() {
    return getFailed() == 0;
  }

  public double getSuccessRate() {
    return (double) getPassed() / getTotal();
  }

  @Override
  public String toString() {
    return String.format("Passed: %d, Failed: %d, Total: %d, Detail: %s", getPassed(), getFailed(), getTotal(),
        getDetails());
  }
}
