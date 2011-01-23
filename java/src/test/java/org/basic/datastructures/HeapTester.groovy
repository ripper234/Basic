package org.basic.datastructures

import org.basic.datastructures.heaps.ArrayHeap
import org.basic.datastructures.heaps.Heap
import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertNull
import static org.testng.AssertJUnit.assertEquals

class HeapTester {
  @Test
  void emptyPeekReturnsNull() {
    def heap = new ArrayHeap<Integer>();
    assertNull(heap.peekMin())
  }

  @Test (expectedExceptions = Exception.class)
  void emptyTakeMinFails() {
    def heap = new ArrayHeap<Integer>();
    heap.takeMin()
  }

  @Test
  void nonEmpty() {
    Heap<Integer> heap = new ArrayHeap<Integer>();
    heap.addAll([7,6,1,3,2,4])
    assertOrderAndTakeAll(heap, [1,2,3,4,6,7])
  }

  void assertOrderAndTakeAll(Heap<Integer> heap, List<Integer> list) {
    for (int i : list) {
      assertEquals i, heap.peekMin()
      assertEquals i, heap.takeMin()
    }
    assertNull heap.peekMin()
  }
}
