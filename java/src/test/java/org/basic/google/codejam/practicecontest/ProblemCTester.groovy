package org.basic.google.codejam.practicecontest

import org.basic.datastructures.graphs.Edge
import org.basic.google.codejam.Tester
import org.testng.annotations.Test
import static com.google.common.collect.Lists.newArrayList

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/11/11
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProblemCTester extends Tester{
  @Test
  public void sample() {
    runTest("C-sample.in")
  }

  @Test
  public void small() {
    runTest("C-small-practice.in")
  }

  @Test
  public void large() {
    runTest("C-large-practice.in")
  }

  @Override protected void test(List<String> lines) {
    int N = Integer.parseInt(lines.get(0));
    int lineNumber = 1;
    for (int i = 1; i <= N; ++i) {
      def (n, k) = readTwoNumbers(lines.get(lineNumber++));
      List<Edge> forbiddenEdges = newArrayList();
      for (int j = 0; j < k; ++j)
      {
        def (u, v) = readTwoNumbers(lines.get(lineNumber++))
        forbiddenEdges.add new Edge(u, v);
      }
      int solution = ProblemCSolver2.solve(n, forbiddenEdges);
      printOutput(i, solution)
    }
  }
}