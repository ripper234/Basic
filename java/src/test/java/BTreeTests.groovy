import TreeTests.Operation
import datastrutcures.BTree
import datastrutcures.ITree
import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals
import com.google.common.collect.Lists
import static org.testng.AssertJUnit.*

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: Jan 1, 2011
 * Time: 5:47:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class BTreeTests {
  @Test
  public void basic() {
    ITree<Integer> tree = new BTree<Integer>();
    assertEquals 0, tree.height()
    tree.add([1, 2, 3, 4, 5, 6, 7]);

    assertEquals 3, tree.height()

    assertEquals([1, 2, 3, 4, 5, 6, 7], tree.toList())
    assertEquals 7, tree.size()
  }

  @Test
  public void sortedOrder() {
    ITree<Integer> tree = new BTree<Integer>();
    assertEquals 0, tree.height()
    tree.add([4, 3, 2, 5, 1, 2, 9, 8, 6]);
    assertEquals([1, 2, 3, 4, 5, 6, 7, 8, 9], tree.toList())
  }

  @Test
  public void deleteExistingNodes() {
    ITree<Integer> tree = new BTree<Integer>();
    assertEquals 0, tree.height()
    tree.add([4, 3, 2, 5, 1, 2, 9, 8, 6]);
    tree.delete(4)
    tree.delete(1);
    assertEquals([2, 3, 5, 6, 7, 8, 9], tree.toList())
  }



  @Test
  public void randomOperations() {
    Random rand = new Random(0)
    final int numOps = 100;
    final int maxInt = 50;
    ITree<Integer> tree = new BTree<Integer>()
    List<Integer> ints = Lists.newArrayList();
    for (int i = 0; i < numOps; ++i) {
      int opVal = rand.nextInt(Operation.values().length)
      Operation op = Operation.values()[opVal]

      // switch doesn't work :(
      // http://jira.codehaus.org/browse/GROOVY-4612
      final int x = rand.nextInt(maxInt)
      if (op == TreeTests.Operation.Add) {
        tree.add x
        ints.add(x);
      }
      else if (op == TreeTests.Operation.Delete) {
        boolean result = tree.delete(x)
        if (ints.remove((Object) (Integer) x)) {
          assertTrue(result);
        }
        else
          assertFalse(result)
      }
      else if (op == TreeTests.Operation.Find) {
        boolean found = tree.exists(x);
        if (ints.contains(x))
          assertTrue(found)
        else
          assertFalse(found)
      }
      else throw new RuntimeException("Unsupported operation " + op);

      assertEquals ints, tree.toList()
      assertEquals ints.size(), tree.size()
    }
  }
}
