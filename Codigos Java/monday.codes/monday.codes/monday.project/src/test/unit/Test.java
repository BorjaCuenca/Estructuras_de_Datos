package test.unit;

/**
 * A test to be tested.
 * @author Pepe Gallardo
 */
public abstract class Test {
  protected static int defaultTimeout = 3;
  protected static boolean logging = true;
  protected int timeout;
  protected String name;

  /**
   * Sets default maximum execution time (in seconds) for each test.
   *
   * @param timeout default maximum execution time (in seconds) for each test.
   */
  static void setDefaultTimeout(int timeout) {
    defaultTimeout = timeout;
  }

  static void setLogging(boolean logging) {
    Test.logging = logging;
  }

  public static void run(java.util.List<Test> tests) {
    for (Test test : tests) {
      test.run();
    }
  }

  public static void run(Test... tests) {
    run(java.util.List.of(tests));
  }

  public static Results results(java.util.List<Test> tests) {
    boolean[] results = new boolean[tests.size()];

    int i = 0;
    for (Test test : tests) {
      results[i] = test.run();
      i++;
    }
    return new Results(results);
  }

  public static Results results(Test... tests) {
    return results(java.util.List.of(tests));
  }

  public boolean run() {
    if(logging) {
      System.out.printf("%s: ", name);
    }
    return true;
  }
}
