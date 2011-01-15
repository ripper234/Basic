package org.basic.google.codejam.practiceproblems.eggdrop

import org.basic.google.codejam.Tester
import org.testng.annotations.Test

class ProblemCTester extends Tester {
  @Test
  public void sample() {
    runTest "sample.in";
  }

  @Test
  public void small() {
    runTest "C-small-practice.in";
  }

  @Test
  public void large() {
    runTest "C-large-practice.in";
  }

  protected void test(List<String> lines) {
    int N = Integer.parseInt(lines.get(0));
    ProblemCSolver solver = new ProblemCSolver();
    for (int i = 1; i <= N; ++i) {
      def (f, d, b) = readThreeNumbers(lines.get(i));
      printOutput i, solver.solve(f, d, b);
    }
  }
}
