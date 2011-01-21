package org.basic.google.codejam.practicecontest

import org.basic.google.codejam.Tester
import org.testng.annotations.Test
import org.basic.datastructures.geometry.Point

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/10/11
 * Time: 7:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProblemBTester extends Tester {
  @Test
  public void sample() {
    runTest("B-sample.in");
  }

  @Test
  public void big() {
    runTest("B-large-practice.in");
  }

  @Test
  public void small() {
    runTest("B-small-practice.in");
  }

  @Override
  protected void test(List<String> lines) {
    int cases = Integer.parseInt(lines.get(0));
    int caseNumber = 0;
    for (int lineNumber = 1; caseNumber < cases; ++lineNumber) {
      ++caseNumber;
      String line = lines[lineNumber];
      def (n, k) = readTwoNumbers(line);
      List<Point> points = new ArrayList<Point>()
      for (int j = 0; j < n; ++j) {
        ++lineNumber;
        line = lines[lineNumber];
        def (x, y) = readTwoNumbers(line);
        points.add(new Point(x, y));
      }
      int solution = ProblemBSolver1.solve(k, points);
      printOutput(caseNumber, solution);
    }
  }


}
