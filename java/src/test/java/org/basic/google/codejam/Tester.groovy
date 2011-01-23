package org.basic.google.codejam

import static com.google.common.collect.Lists.newArrayList
import java.text.DecimalFormat

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/10/11
 * Time: 7:16 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Tester {
  static List<Integer> readNumbers(String line) {
    List<Integer> result = newArrayList();
    for (String str : line.split("\\s")) {
      int i = Integer.parseInt(str);
      result.add(i);
    }
    return result;
  }

  static List<Integer> readTwoNumbers(String line) {
    def match = line =~ /(\d+) (\d+)/;
    return [Integer.parseInt(match[0][1]), Integer.parseInt(match[0][2])];
  }

  static List<Long> readThreeNumbers(String line) {
    def match = line =~ /(\d+) (\d+) (\d+)/;
    return [Long.parseLong(match[0][1]), Long.parseLong(match[0][2]), Long.parseLong(match[0][3])];
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

  static <T> List<T> skip(List<T> objects, int skip) {
    return objects.subList(skip, objects.size() - skip);
  }

  static String doubleToString(double d) {
   DecimalFormat decim = new DecimalFormat("0.0000000");
   final String str = decim.format(d)
   return str
 }
}
