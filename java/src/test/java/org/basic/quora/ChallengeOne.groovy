package org.basic.quora

import org.testng.annotations.Test
import org.basic.google.codejam.Tester

public class ChallengeOneTester extends Tester {
  @Test
  public void test() {
    runTest "input1.txt"
  }

  @Override protected void test(List<String> lines) {
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
    System.out.println(ChallengeOne.countDuct(grid));
  }
}
