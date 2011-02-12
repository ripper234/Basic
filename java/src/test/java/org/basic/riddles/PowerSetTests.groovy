package org.basic.riddles

import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals
import static org.basic.riddles.PowerSet.getPset

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 2/12/11
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class PowerSetTests {
  @Test
  public void small() {
    Set<Integer> foo = new HashSet<Integer>([1, 2, 3]);
    Set<Set<Integer>> pset = getPset(foo);
    assertEquals(8, pset.size())
  }

  @Test
  public void medium() {
    Set<Integer> foo = new HashSet<Integer>([1, 2, 3, 4, 5, 6]);
    Set<Set<Integer>> pset = getPset(foo);
    assertEquals(64, pset.size())
  }

}