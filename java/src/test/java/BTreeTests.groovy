import TreeTests.Operation
import datastrutcures.trees.BTree
import datastrutcures.trees.ITree
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
  public void insert2() {
    ITree<Integer> tree = new BTree<Integer>();
    tree.put 1, 10
    tree.put 2, 20

    assertEquals([1, 2], tree.keys())
    assertEquals 2, tree.size()
  }

  @Test
  public void insert3() {
    ITree<Integer> tree = new BTree<Integer>();
    tree.put 1, 10
    tree.put 2, 20
    tree.put 3, 30

    assertEquals([1, 2, 3], tree.keys())
    assertEquals 3, tree.size()
  }

  @Test
  public void insert4() {
    ITree<Integer> tree = new BTree<Integer>();
    tree.put 1, 10
    tree.put 2, 20
    tree.put 3, 30
    tree.put 4, 40

    assertEquals([1, 2, 3, 4], tree.keys())
    assertEquals 4, tree.size()
  }

  @Test
  public void basic() {
    ITree<Integer> tree = new BTree<Integer>();
    assertEquals 0, tree.height()
    putTree(tree, [1, 2, 3, 4, 5, 6, 7]);

    assertEquals 3, tree.height()

    assertEquals([1, 2, 3, 4, 5, 6, 7], tree.keys())
    assertEquals 7, tree.size()
  }

  @Test
  public void sortedOrder() {
    ITree<Integer> tree = new BTree<Integer>();
    assertEquals 0, tree.height()
    putTree(tree, [4, 3, 2, 5, 1, 2, 9, 8, 6]);
    assertEquals([1, 2, 3, 4, 5, 6, 7, 8, 9], tree.keys())
  }

  @Test
  public void deleteExistingNodes() {
    ITree<Integer> tree = new BTree<Integer>();
    assertEquals 0, tree.height()
    putTree(tree, [4, 3, 2, 5, 1, 2, 9, 8, 6]);
    tree.delete(4)
    tree.delete(1);
    assertEquals([2, 3, 5, 6, 7, 8, 9], tree.keys())
  }

  private void putTree(ITree<Integer, Integer> tree, List<Integer> keys) {
    keys.each({x -> addX(x, tree)})
  }

  private void addX(int x, ITree<Integer, Integer> tree) {
    tree.put x, x * x;
    System.out.println("Successfully added ${x}");
  }

  @Test
  public void randomOperations() {
    Random rand = new Random(0)
    final int numOps = 100;
    final int maxInt = 50;
    ITree<Integer, Integer> tree = new BTree<Integer, Integer>()
    List<Integer> keys = Lists.newArrayList();
    for (int i = 0; i < numOps; ++i) {
      int opVal = rand.nextInt(Operation.keys().length)
      Operation op = Operation.keys()[opVal]

      // switch doesn't work :(
      // http://jira.codehaus.org/browse/GROOVY-4612
      final int x = rand.nextInt(maxInt)
      if (op == TreeTests.Operation.Add) {
        tree.put x
        keys.add(x);
      }
      else if (op == TreeTests.Operation.Delete) {
        boolean result = tree.delete(x)
        if (keys.remove((Object) (Integer) x)) {
          assertTrue(result);
        }
        else
          assertFalse(result)
      }
      else if (op == TreeTests.Operation.Find) {
        String found = tree.find(x);
        if (keys.contains(x))
          assertNotNull(found)
        else
          assertNull(found)
      }
      else throw new RuntimeException("Unsupported operation " + op);

      assertEquals keys, tree.keys()
      assertEquals keys.size(), tree.size()
    }
  }
}
