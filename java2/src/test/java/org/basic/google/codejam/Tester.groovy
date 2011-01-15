package org.basic.google.codejam

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/10/11
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Tester {
  static List<Integer> readTwoNumbers(String line) {
    def match = line =~ /(\d+) (\d+)/;
    return [Integer.parseInt(match[0][1]), Integer.parseInt(match[0][2])];
  }

  static List<Integer> readThreeNumbers(String line) {
    def match = line =~ /(\d+) (\d+) (\d+)/;
    return [Integer.parseInt(match[0][1]), Integer.parseInt(match[0][2]), Integer.parseInt(match[0][3])];
  }

  protected static void printOutput(int caseNumber, Object solution) {
    System.out.println("Case #${caseNumber}: ${solution}")
  }

  protected List<String> getLines(String filename) {
    String path = getPath()
    return new File(path, filename).readLines()
  }

  protected void runTest(String filename) {
    List<String> lines = getLines(filename)
    test(lines);
  }
  protected abstract void test(List<String> lines);

  private String getPath() {
    String path = "src\\test\\java\\" + getClass().getName().replace(".", "\\").replace(getClass().getSimpleName(), "");
    return path;
  }
}
