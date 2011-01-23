package org.basic.datastructures

import org.basic.datastructures.sorting.HeapSorter
import org.basic.datastructures.sorting.QuickSorter3
import org.basic.datastructures.sorting.Sorter
import org.testng.Assert
import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/8/11
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SortTester {
  @Test
  void testHeapSorter() {
    testSorter(new HeapSorter())
  }

  @Test
  void testQuickSorter3() {
    testSorter(new QuickSorter3())
  }

  private void testSorter(Sorter sorter) {
    Random rand = new Random(0);
    for (int size = 1; size < 30; ++size) {
      int[] arr = randomArray(size, rand);
      testArray(arr, sorter);
    }
  }

  @Test
  void testSpecificArray() {
    Sorter sorter = new QuickSorter3();
    testArray([2, 4, 3, 5, 2, 1], sorter);
  }

  private void testArray(List<Integer> list, QuickSorter3 sorter) {
    int[] arr = list.toArray(new int[list.size()]);
    testArray(arr, sorter);
  }

  private void testArray(int[] expected, Sorter sorter) {
    int[] actual = Arrays.copyOf(expected, expected.length);

    System.out.println("Testing array: ${expected}");
    Arrays.sort(expected);
    actual = sorter.sort(actual);
    assertEqualArrays(actual, expected)
  }

  private def assertEqualArrays(int[] actual, int[] expected) {
    assertEquals actual.length, expected.length
    for (int i = 0; i < expected.length; ++i)
      assertEquals actual[i], expected[i]
    
    // doesn't work for some reason
    // Assert.assertEquals(actual, expected)
  }

  private int[] randomArray(int size, Random rand) {
    int[] result = new int[size];
    for (int i = 0; i < size; ++i)
      result[i] = rand.nextInt(size);
    return result;
  }
}
