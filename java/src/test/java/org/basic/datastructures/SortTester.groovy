package org.basic.datastructures

import org.basic.datastructures.heaps.IntHeapSorter1
import org.basic.datastructures.heaps.IntHeapSorter2
import org.testng.annotations.Test
import org.basic.datastructures.sorting.*
import static org.testng.AssertJUnit.assertEquals

public class IntHeapSort2Tester extends SortTester {
  protected Sorter createSorter() {
    return new IntHeapSorter2();
  }
}

public class IntHeapSort1Tester extends SortTester {
  protected Sorter createSorter() {
    return new IntHeapSorter1();
  }
}

public class HeapSortTester extends SortTester {
  protected Sorter createSorter() {
    return new HeapSorter();
  }
}

public class QuickSortTester extends SortTester {
  protected Sorter createSorter() {
    return new QuickSorter3();
  }
}

public class BubbleSortTester extends SortTester {
  protected Sorter createSorter() {
    return new BubbleSort();
  }
}

public class MergeSortTester extends SortTester {
  protected Sorter createSorter() {
    return new MergeSort();
  }
}

public abstract class SortTester {
  protected abstract Sorter createSorter();

  @Test
  void testSorter() {
    Sorter sorter = createSorter();
    Random rand = new Random(0);
    for (int size = 1; size < 200; ++size) {
      int[] arr = randomArray(size, rand);
      testArray(arr, sorter);
    }
  }

  @Test
  void testSpecificArray(){
    Sorter sorter = createSorter();
    testArray([2, 4, 3, 5, 2, 1], sorter);
  }

  private void testArray(List<Integer> list, Sorter sorter) {
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
