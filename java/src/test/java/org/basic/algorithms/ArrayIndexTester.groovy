package org.basic.algorithms

import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals

public class ArrayIndexTester {
  @Test
  void sanity() {
    test(4, 5, [1,2,3,4,5,6]);
    test(2, 3, [1,2,3,4,5,6]);
    test(2, 3, [6,5,4,3,2,1]);
  }

  void test(int i, int expectedIndex, List<Integer> integers) {
    int[] array = integers.toArray(new int[integers.size()])
    int found = ArrayIndex.find(array, i);
    assertEquals(expectedIndex, found);
  }
}
