package org.basic.algorithms

import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals

public class KnapsackTester {
  @Test
  void basic() {
    Knapsack sack = new Knapsack([0, 10], [1, 3], [2, 5], [2, 8]);
    int effectiveness = sack.run (16, 24, 10);
    assertEquals (effectiveness, 22);
  }
}