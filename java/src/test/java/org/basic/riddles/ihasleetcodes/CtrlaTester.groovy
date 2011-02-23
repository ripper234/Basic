package org.basic.riddles.ihasleetcodes

import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals

class CtrlATester {

  @Test
  void test() {
    assertEquals(48, CtrlA.solve(15))
    assertEquals(64, CtrlA.solve(16))
    assertEquals(192, CtrlA.solve(21))
    assertEquals(500, CtrlA.solve(25))
    assertEquals(625, CtrlA.solve(26))
    assertEquals(768, CtrlA.solve(27))
  }
}
