package org.basic.google.codejam.contest2008

import org.basic.datastructures.geometry.Point
import org.basic.google.codejam.Tester
import org.testng.annotations.Test

class ProblemATester extends Tester{
  @Test
  public void sample () {
    runTest "sample-A.in"
  }

  @Test
  public void small () {
    runTest "A-small-practice.in"
  }

  @Test
  public void large () {
    runTest "A-large-practice.in"
  }

  protected void test(List<String> lines) {
    int n = Integer.parseInt(lines.get(0));
    for (int line = 1; line <= n; ++line)
    {
      def (x1, y1, x2, y2, x3, y3) = readNumbers(lines.get(line))
      String result = Triangle.classify(new Point(x1, y1), new Point(x2, y2), new Point(x3, y3))
      printOutput line, result
    }
  }
}
