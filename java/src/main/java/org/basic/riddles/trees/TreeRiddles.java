package org.basic.riddles.trees;

import static org.basic.utils.MathUtils.log;
import static org.basic.utils.MathUtils.pow2;
import static org.testng.Assert.assertNull;

public class TreeRiddles {
    public static Tree buildNearCompleteTree(int n) {
        // builds the near-complete tree with n vertices
        if (n == 0)
            return null;

        if (n == 1)
            return new Tree();

        // k = height in nodes of entire tree
        // 2^(k-1) <= n <= 2^k-1
        // 2^k <= 2*n   ,   2^k >= n+1
        // n+1 <= 2^k <= 2*n
        // log(n+1) <= k <= log(n)+1
        // k = (int)(log(n))+1
        //
        // now, either left or right are complete, and the other is near complete.
        // If (n-1) has enough nodes to build two complete trees of height k-2 and k-1,
        // let's build the complete left tree and the remainder we'll put in the right
        // otherwise, let's build a smaller complete tree to the right and put the remainder left
        int k = log(n) + 1;
        int left;
        int right;
        if (n - 1 >= sizeOfCompleteTree(k - 1) + sizeOfCompleteTree(k - 2)) {
            left = sizeOfCompleteTree(k - 1);
            right = n - 1 - left;
        } else {
            right = sizeOfCompleteTree(k - 2);
            left = n - 1 - right;
        }

        Tree result = new Tree();
        result.left = buildNearCompleteTree(left);
        result.right = buildNearCompleteTree(right);
        return result;
    }

    public static Tree buildNearCompleteTree2(int n) {
        // build a full tree larger than n, then delete
        int height = log(n) + 1;
        Tree tree = buildCompleteTree(height);
        int leftover = pow2(height) - 1 - n;
        deleteNodesFromCompleteTree(tree, leftover);
        return tree;
    }

    private static int deleteNodesFromCompleteTree(Tree tree, int leftover) {
        if (leftover == 0)
            return 0;

        if (tree.right == null) {
            assertNull(tree.left);
            throw new RuntimeException("Can't delete from an empty tree");
        }

        if (tree.right.leaf()) {
            leftover--;
            tree.right = null;
            if (leftover > 0)
            {
                leftover--;
                tree.left = null;
            }
            return leftover;
        }

        leftover = deleteNodesFromCompleteTree(tree.right, leftover);
        if (leftover > 0)
            leftover = deleteNodesFromCompleteTree(tree.left, leftover);
        return leftover;
    }

    private static Tree buildCompleteTree(int height) {
        if (height == 0)
            return null;
        Tree tree = new Tree();
        tree.left = buildCompleteTree(height - 1);
        tree.right = buildCompleteTree(height - 1);
        return tree;
    }

    private static int sizeOfCompleteTree(int k) {
        return pow2(k) - 1;
    }
}

