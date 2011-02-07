package org.basic.datastructures

import org.basic.datastructures.IntTrie
import org.testng.annotations.Test
import static org.testng.Assert.assertEquals
import static org.testng.AssertJUnit.assertFalse
import static org.testng.AssertJUnit.assertTrue

public class IntTrieTests {
  @Test
  void test() {
    IntTrie trie = new IntTrie();
    trie.insert([1, 50, 123, 127, 5, 3, 4, 28]);
    testFind(trie, [1: true, 40: false, 123: true, 17: false, 28: true, 2 : false, 8 : false])
    assertTrue(trie.find(50))
    assertTrue(trie.find(123))
    trie.delete(50);
    trie.delete(123);
    assertFalse(trie.find(50))
    assertFalse(trie.find(123))
    System.out.println(trie.countMod(0, 1));
  }

  @Test
  public void countMod(){
    def trie = new IntTrie([50, 4, 28])
    assertEquals(3, trie.countMod(0, 1));

    trie = new IntTrie([1, 50, 123, 127, 5, 3, 4, 28])
    assertEquals(3, trie.countMod(0, 1));
  }

  private void testFind(IntTrie trie, def map) {
    map.each({
      System.out.println("Key: ${it.key}, Val: ${it.value}");
      def found = trie.select(it.key);
      assertEquals(it.value, found);
    });
  }

}