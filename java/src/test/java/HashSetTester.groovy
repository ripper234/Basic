import datastrutcures.HashMap
import datastrutcures.IHashMap

import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals
import static org.testng.AssertJUnit.assertNull
import static org.testng.AssertJUnit.assertTrue
import static org.testng.AssertJUnit.assertFalse

public class HashSetTester {
  @Test
  public void simple(){
    IHashMap<String, Integer> set = new HashMap<String, Integer>();

    set.put("Dog", 20);
    set.put("Cat", 10);
    set.put("Mouse", 30);

    assertEquals 3, set.size()
    assertEquals 20, set.get("Dog")
    assertEquals 30, set.get("Mouse");
    assertNull set.get("mouse")
    assertNull set.get("Foo")

    set.clear()

    assertEquals 0, set.size()
  }

  @Test
  public void delete() {
    IHashMap<String, Integer> set = new HashMap<String,Integer>()
    set.put "Foo", 1
    set.put "Key", 2

    assertTrue set.delete("Foo")
    assertFalse set.delete("Baba");

    assertEquals 1, set.size()
  }

}