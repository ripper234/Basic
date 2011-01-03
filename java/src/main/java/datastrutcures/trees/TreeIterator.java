package datastrutcures.trees;

import datastrutcures.Pair;

import java.util.Iterator;

import static org.testng.AssertJUnit.assertNotNull;

public class TreeIterator<TKey extends Comparable<TKey>, TValue> implements Iterator<Pair<TKey, TValue>> {
    private TreeNode<TKey, TValue> current;

    public TreeIterator(TreeNode<TKey, TValue> root) {
        current = root;
        while (current.left != null)
            current = current.left;
    }

    public boolean hasNext() {
        return current != null;

    }

    public Pair<TKey, TValue> next() {
        assertNotNull(current.value);
        Pair<TKey,TValue> result = current.toPair();

        // find next node
        current = current.findNodeGreaterThan(current.key1);
        return result;
    }

    public void remove() {
        throw new RuntimeException("Not implemented");
    }
}
