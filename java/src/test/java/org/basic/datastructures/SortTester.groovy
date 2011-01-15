package org.basic.datastructures

import org.basic.datastructures.sorting.QuickSorter3
import org.basic.datastructures.sorting.Sorter
import org.testng.Assert
import org.testng.annotations.Test

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/8/11
 * Time: 8:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SortTester {
  @Test
  void testQuickSorter3() {
    Sorter sorter = new QuickSorter3();
    Random rand = new Random(0);
    for (int size = 1; size < 30; ++size) {
      int[] arr = randomArray(size, rand);
      testArray(arr, sorter);
    }
  }

  @Test
  void testSpecificArray(){
    Sorter sorter = new QuickSorter3();
    testArray([2, 4, 3, 5, 2, 1], sorter);
  }

  private void testArray(List<Integer> list, QuickSorter3 sorter) {
    int[] arr = list.toArray(new int[list.size()]);
    testArray(arr, sorter);
  }

  private void testArray(int[] arr, QuickSorter3 sorter) {
    int[] arr2 = Arrays.asList(arr).toArray(new int[arr.length]);

    System.out.println("Testing array: ${arr}");
    Arrays.sort(arr);
    sorter.sort(arr2);
    Assert.assertEquals(arr, arr2)
  }

  private int[] randomArray(int size, Random rand) {
    int[] result = new int[size];
    for (int i = 0; i < size; ++i)
      result[i] = rand.nextInt(size);
    return result;
  }
}
