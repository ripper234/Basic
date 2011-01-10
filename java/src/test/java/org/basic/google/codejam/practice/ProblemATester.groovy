package org.basic.google.codejam.practice

import org.testng.annotations.Test
import org.basic.google.codejam.Tester


class ProblemATester extends Tester{
  @Test
  public void small() {
    runTest("A-small-practice.in");
  }

  @Test
  public void large() {
    runTest("A-large-practice.in");
  }

  @Override
  protected void test(List<String> lines) {
    String header = lines.get(0);
    int n = Integer.parseInt(header);
    for (int i = 1; i <= n; ++i) {
      String line = lines.get(i);
      def match = line =~ /(\d+) (\d+)/
      int white = Long.parseLong(match[0][1]);
      int black = Long.parseLong(match[0][2]);

      String solution = ProblemASolver2.staticSolve(white, black);
      System.out.println("Case #${i}: ${solution}");
    }
  }
}
