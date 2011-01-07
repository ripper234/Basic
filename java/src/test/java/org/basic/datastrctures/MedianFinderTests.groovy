package org.basic.datastrctures

import org.junit.Test
import static org.testng.AssertJUnit.assertEquals
import org.basic.medians.DumbMedianFinder
import org.basic.medians.IMedianFinder
import org.basic.medians.SmartMedianFinder
import static org.testng.AssertJUnit.assertTrue
import org.basic.ListSplitter

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Jan 7, 2011
 * Time: 12:12:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class MedianFinderTests {
  @Test
  public void splitter() {
    assertEquals([[1], [2]], ListSplitter.splitToNLists([1, 2], 2));
    assertEquals([[1], [2, 3]], ListSplitter.splitToNLists([1, 2, 3], 2));
    assertEquals([[1, 2], [3, 4]], ListSplitter.splitToNLists([1, 2, 3, 4], 2));
    assertEquals([[1], [2], [3, 4]], ListSplitter.splitToNLists([1, 2, 3, 4], 3));
    assertEquals([[1], [2], [3], [4]], ListSplitter.splitToNLists([1, 2, 3, 4], 4));
    assertEquals([[1], [2], [3], [4]], ListSplitter.splitToNLists([1, 2, 3, 4], 5));
  }

  @Test
  public void partition() {
    testPartition(4, [1, 4, 2, 3, 6]);
    testPartition(4, [1, 2, 3, 6, 4]);
    testPartition(4, [1, 2, 3, 4, 6, 4]);
    testPartition(3, [1, 2, 3, 4, 6, 4]);
    testPartition(3, [1, 2, 3, 4, 6, 4, 3, 3, 7, 12, 24]);
    testPartition(5, [5, 4, 6, 1, 2, 3, 7]);
  }

  enum PartitionPhase {
    Small, Equal, Large
  };

  private void testPartition(x, List<Integer> list) {
    int index = SmartMedianFinder.partition(list, x);
    assertEquals(x, list.get(index));
    PartitionPhase phase = PartitionPhase.Small;
    for (int y: list) {
      if (phase == PartitionPhase.Small) {
        assertTrue(y <= x);
        if (y == x)
          phase = PartitionPhase.Equal;
        continue;
      }
      if (phase == PartitionPhase.Equal) {
        assertTrue(y >= x);
        if (y > x)
          phase = PartitionPhase.Large;
        continue;
      }
      assertTrue(y > x);
    }

  }

  @Test
  public void sanity() {
    IMedianFinder dumb = new DumbMedianFinder();
    assertEquals(1, dumb.findMedian([1]))
    assertEquals(2, dumb.findMedian([1, 2]))
    assertEquals(2, dumb.findMedian([1, 2, 3]))
    assertEquals(3, dumb.findMedian([1, 2, 3, 4]))
    assertEquals(3, dumb.findMedian([5, 3, 1, 2, 4]))
  }

  @Test
  public void random() {
    for (int size = 1; size < 21; ++size) {
      def list = [];
      Random rand = new Random(0);
      for (int i = 0; i < size; ++i)
        list.add(rand.nextInt(size * size));

      System.out.println("Testing list of size ${size}: ${list}");
      testList(list)
    }
  }

  @Test
  public void simple3() {
    testList([1, 2, 3])
  }

  @Test
  public void simple5() {
    testList([5, 4, 6, 2, 7])
  }

  @Test
  public void simple7() {
    testList([5, 4, 6, 1, 2, 3, 7])
  }

  @Test
  public void simple6() {
    testList([24, 16, 13, 11, 35, 29])
  }

  private void testList(List list) {
    IMedianFinder dumb = new DumbMedianFinder();
    IMedianFinder smart = new SmartMedianFinder();
    assertEquals(dumb.findMedian(list), smart.findMedian(list))
  }
}
