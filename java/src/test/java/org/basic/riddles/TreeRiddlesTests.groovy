package org.basic.riddles

import org.testng.annotations.Test
import static org.testng.AssertJUnit.assertEquals
import static org.testng.AssertJUnit.assertTrue
import org.basic.riddles.trees.Tree
import org.basic.riddles.trees.TreeRiddles

/**
 * Created by IntelliJ IDEA.
 * User: ron
 * Date: 1/8/11
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class TreeRiddlesTests {
  @Test
  public void nearCompleteTree() {
    for (int size = 1; size <= 16; ++size) {
      Tree tree = TreeRiddles.buildNearCompleteTree2(size);
      System.out.println("Built near complete tree of size ${size}");
      assertEquals(size, tree.size())
      assertTrue(isNearCompleteTree(tree))
    }
  }

  private boolean isNearCompleteTree(Tree tree) {
    if (tree == null)
      return true;
    if (tree.right == null) {
      if (tree.left == null)
        return true;

      if (tree.left.size() != 1)
        return false;

      return isNearCompleteTree(tree.left);
    }
    if (tree.left == null)
      return false;

    return isNearCompleteTree(tree.left) && isNearCompleteTree(tree.right) &&
            (isCompleteTree(tree.left) || isCompleteTree(tree.right));
  }

  private boolean isCompleteTree(Tree tree) {
    if (tree == null)
      return true;
    if (tree.left == null) {
      if (tree.right == null)
        return true;
      return false;
    }
    if (tree.right == null)
      return false;

    if (tree.left.size() != tree.right.size())
      return false;

    return isCompleteTree(tree.left) && isCompleteTree(tree.right);
  }
}
