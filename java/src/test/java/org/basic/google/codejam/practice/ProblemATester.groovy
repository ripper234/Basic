package org.basic.google.codejam.practice

import org.testng.annotations.Test

class ProblemATester {
  @Test
  public void small() {
    test("A-small-practice.in");
  }

  @Test
  public void large() {
    test("A-large-practice.in");
  }

  private def test(String filename) {
    String path = "src\\test\\java\\" + ProblemATester.class.getName().replace(".", "\\").replace(ProblemATester.getSimpleName(), "");
    List<String> lines = new File(path, filename).readLines()
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
