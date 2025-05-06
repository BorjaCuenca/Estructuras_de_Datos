package test.unit;

import java.util.Locale;
import java.util.StringJoiner;

/**
 * A suite of tests to be tested.
 * @author Pepe Gallardo
 */
public class TestSuite {

  private final String name;
  private final Test[] tests;

  public TestSuite(String name, Test... tests) {
    this.name = name;
    this.tests = tests;
  }

  public static void printAllResults(Results[] allResults) {
    StringJoiner sjRatios = new StringJoiner(" ");
    StringJoiner sjDetails = new StringJoiner(";;");
    StringJoiner sjPassed = new StringJoiner(";");
    StringJoiner sjSuccessRates = new StringJoiner(";");

    for (Results results : allResults) {
      sjRatios.add(String.format("%d/%d", results.getPassed(), results.getTotal()));
      sjPassed.add(String.valueOf(results.getPassed()));
      sjSuccessRates.add(String.format(Locale.ENGLISH, "%.3f", results.getSuccessRate()));
      StringJoiner sjSet = new StringJoiner(";");
      for (char c : results.getDetails().toCharArray()) {
        sjSet.add(String.valueOf(c));
      }
      sjDetails.add(sjSet.toString());
    }
    System.out.printf("%s\n", sjRatios);
    System.out.printf("%s\n", sjDetails);
    System.out.printf("%s\n", sjPassed);
    System.out.printf("%s\n", sjSuccessRates);
  }

  public static Results[] run(TestSuite... testSuites) {
    Results[] allResults = new Results[testSuites.length];

    int i = 0;
    for (TestSuite testSuite : testSuites) {
      allResults[i] = testSuite.run();
      if (Test.logging) {
        System.out.printf("\n%s\n\n\n", allResults[i]);
      }
      i++;
    }

    if (Test.logging) {
      printAllResults(allResults);
    }
    return allResults;
  }

  public Results run() {
    if (Test.logging) {
      String message = "Tests for " + name;
      String separator = "=".repeat(message.length());
      System.out.printf("%s\n%s\n", message, separator);
    }
    boolean[] results = new boolean[tests.length];
    int i = 0;
    for (Test test : tests) {
      results[i] = test.run();
      i++;
    }
    return new Results(results);
  }
}
