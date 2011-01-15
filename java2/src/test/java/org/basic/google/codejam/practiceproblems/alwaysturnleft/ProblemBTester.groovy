package org.basic.google.codejam.practiceproblems.alwaysturnleft

import org.basic.google.codejam.Tester
import org.testng.annotations.Test

class ProblemBTester extends Tester {
  @Test
  public void sample() {
    runTest "sample.in"
  }

  @Test
  public void small() {
    runTest "B-small-practice.in"
  }

  @Test
  public void large() {
    runTest "B-large-practice.in"
  }


  protected void test(List<String> lines) {
    int N = Integer.parseInt(lines.get(0));

    for (int i = 1; i <= N; ++i) {
      final String line = lines.get(i)
      def match = line =~ /(.+) (.+)/;
      def (normal, opposite) = [match[0][1], match[0][2]]
      String result = ProblemBSolver.reconstructMaze(normal, opposite);
      printOutput i, result
    }
  }
}
