package org.basic.riddles

import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/7/11
 * Time: 7:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class RiddlesTests {
  @Test
  public void longestSublist() {
    assertAscending(4, [4,3,1,2,5,7,3,1]);
    assertAscending(1, [4,3,1]);
    assertAscending(0, []);
  }

  private void assertAscending(int expectedLength, List<Integer> list) {
    int actualLength = Riddles.findMaxAscendingList(list);
    assertEquals(expectedLength, actualLength);
  }
}

