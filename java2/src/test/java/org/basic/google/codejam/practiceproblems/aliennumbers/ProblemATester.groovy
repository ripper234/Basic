package org.basic.google.codejam.practiceproblems.aliennumbers

import org.basic.google.codejam.Tester
import org.testng.annotations.Test

public class ProblemATester extends Tester {
  @Test
  public void sample(){
    runTest "sample.in";
  }

  @Test
  public void small(){
    runTest "A-small-practice.in";
  }

  @Test
  public void large(){
    runTest "A-large-practice.in";
  }

  protected void test(List<String> lines) {
    int N = Integer.parseInt(lines.get(0));

    for (int i = 1; i <= N; ++i) {
      final String line = lines.get(i)
      def match = line =~ /(.+) (.+) (.+)/;
      def (number, source, target) = [match[0][1], match[0][2], match[0][3]] 
      String result = ProblemASolver.translate(number, source, target);
      printOutput i, result 
    }
  }
}
