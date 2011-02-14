package org.basic.riddles.ihasleetcodes

import org.testng.annotations.Test
import static org.testng.Assert.assertEquals
import static org.basic.riddles.ihasleetcodes.SelectKInMatrix.select

public class SelectKInMatrixTester {
  @Test
  void one() {
    final int n = 4
    int[][] arr = new int[n][n];
    for (int i = 0; i < n; ++i)
    for (int j = 0; j < n; ++j)
    arr[i][j] = 2*n-i-j;

    assertEquals(select(arr, 1), 8);
    assertEquals(select(arr, 2), 7);
    assertEquals(select(arr, 3), 7);
    assertEquals(select(arr, 4), 6);

    // not supported yet
//    assertEquals(select(arr, 5), 6);
//    assertEquals(select(arr, 6), 6);
//    assertEquals(select(arr, 7), 5);
//    assertEquals(select(arr, 8), 5);
//    assertEquals(select(arr, 9), 5);
//    assertEquals(select(arr, 10), 5);
//    assertEquals(select(arr, 11), 4);
  }

  @Test
  void two() {
    Random rand = new Random(0);
    int n = 20;
    int[][] arr = new int[n][n];
    for (int i = n-1; i >= 0; --i) {
      for (int j = n-1; j >= 0; --j) {
        int down = 0;
        if (i < n-1)
          down = arr[i+1][j];
        int right = 0;
        if (j < n-1)
          right = arr[i][j+1];
        arr[i][j] = Math.max(down, right) + rand.nextInt(4);
      }
    }

    System.out.println(select(arr, 1));
    System.out.println(select(arr, 4));
    System.out.println(select(arr, 7));
    System.out.println(select(arr, 14));

    // todo - not working :(
    System.out.println(select(arr, 20));
  }
}