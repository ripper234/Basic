package org.basic.quora

import org.apache.commons.lang.time.StopWatch
import org.basic.google.codejam.Tester
import org.testng.annotations.Test

public class ChallengeOneTester extends Tester {
  @Test
  public void officialTest() {
    runTest "official.txt"
  }

  @Test
  public void smallTest() {
    runTest "small.txt"
  }

  @Override protected void test(List<String> lines) {
    StopWatch timer = new StopWatch();
    timer.start()

    def (w, h) = readNumbers(lines.get(0))
    int[][] grid = new int[h][w];
    for (int y = 0; y < h; ++y) {
      ArrayList<Integer> row = readNumbers(lines.get(y + 1));
      for (int x = 0; x < w; ++x)
        grid[y][x] = row[x];
    }

//    for (int y = 0; y < h; ++y) {
//      for (int x = 0; x < w; ++x) {
//        System.out.print(grid[y][x]);
//      }
//      System.out.println();
//    }
    final long duct = ChallengeOne.countDuct(grid)
    System.out.println("Solution: ${duct} in ${timer.getTime() / 1000} seconds");
  }
}
