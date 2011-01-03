package datastrutcures.trees;

import datastrutcures.Pair;

import static org.testng.Assert.assertNotSame;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

class TreeNode<TKey extends Comparable<TKey>, TValue> {
    TreeNode<TKey, TValue> left, middle, right;
    TKey key1;
    TKey key2;
    TValue value;
    TreeNode<TKey, TValue> parent;

    TreeNode(TKey key, TValue value) {
        this.key1 = key;
        this.value = value;
    }

    TreeNode<TKey, TValue> find(TKey key) {
        int comparison1 = key.compareTo(key1);
        if (comparison1 == 0) {
            if (value != null) {
                // leaf
                assertNull(left);
                assertNull(middle);
                assertNull(right);
                return this;
            }

            // otherwise, continue to middle
            assertNotNull(middle);
            return middle.find(key);
        }

        if (value != null)
            return null; // leaf, and didn't find

        if (comparison1 < 0) {
            // continue to left tree
            assertNotNull(left);
            return left.find(key);
        }

        // else, comparison1 > 0, the key is either in middle or right tree
        if (key2 == null) {
            // 2 children, let's go middle (2nd child)
            return middle.find(key);
        }
        // 3 children
        assertNotNull(right);

        int comparison2 = key.compareTo(key2);
        if (comparison2 < 0) {
            // middle
            return middle.find(key);
        }
        // otherwise, go right
        return right.find(key);
    }

    /**
     * If this returns not null, the caller must take care to insert the return value.
     */
    TreeNode<TKey, TValue> put(TKey key, TValue value) {
        int comparison1 = key.compareTo(key1);
        if (comparison1 == 0) {
            if (this.value != null) {
                // leaf
                assertNull(left);
                assertNull(middle);
                assertNull(right);

                // update value and we're done
                this.value = value;
                return null;
            }

            // otherwise, continue to middle
            assertNotNull(middle);
            final TreeNode<TKey, TValue> added = middle.put(key, value);
            // there shouldn't be any work here, the key is alread in the tree
            assertNull(added);
            return null;
        }

        if (value != null) {
            // leaf, our caller must work
            return newTreeNode(key, value);
        }

        if (comparison1 < 0) {
            // continue to left tree
            assertNotNull(left);
            final TreeNode<TKey, TValue> node = left.put(key, value);
            if (node == null)
                return null;

            // if we don't have a right node, we can create it and be done
            if (right == null) {
                // the new key is smaller than key1. Let the current middle be the new right

                addAsThirdChild(node);
                return null;
            }

            // we have 3 nodes, we can't add anything at this level.
            // Current node will transform into a 2-node, and we'll return a new tree that contains
            // the right child together with the new child
            TreeNode<TKey, TValue> newNode = newTreeNode(key, null);
            newNode.setLeft(node);
            newNode.key1 = key2;
            newNode.setMiddle(right);
            setRight(null);
            this.key2 = null;
            return newNode;
        }

        // else, comparison1 > 0, the key is either in middle or right tree
        if (key2 == null) {
            // 2 children, let's go middle (2nd child)
            TreeNode<TKey, TValue> newNode = middle.put(key, value);
            if (newNode == null)
                return null;

            // let's add the new node as a 3rd child
            addAsThirdChild(newNode);
            return null;
        }

        // we have 3 children
        assertNotNull(right);

        int comparison2 = key.compareTo(key2);
        if (comparison2 < 0) {
            // middle
            final TreeNode<TKey, TValue> newNode = middle.put(key, value);
            if (newNode == null)
                return null;

            // current node will become a 2-node with the new key
            // We will return a new node with our current middle and right nodes
            TreeNode<TKey, TValue> result = newTreeNode(key2, null);
            result.key1 = key2;
            result.setLeft(middle);
            result.setMiddle(right);
            setRight(null);
            setMiddle(newNode);

            return newNode;
        }

        // otherwise, go right
        final TreeNode<TKey, TValue> newNode = right.put(key, value);
        if (newNode == null)
            return null;

        // the new node and our current right node will become a new node
        // comparison2 should be < 0 here
        assertNotSame(0, comparison2);
        TreeNode<TKey, TValue> result = newTreeNode(key2, null);
        result.setLeft(right);
        result.setMiddle(newNode);
        setRight(null);
        return result;
    }

    void setMiddle(TreeNode<TKey, TValue> node) {
        middle = node;
        node.parent = this;
    }

    void setLeft(TreeNode<TKey, TValue> node) {
        left = node;
        node.parent = this;
    }

    int height() {
        if (value != null) {
            // leaf
            assertNull(left);
            assertNull(middle);
            assertNull(right);
            return 1;
        }
        return left.height() + 1;
    }

    private void addAsThirdChild(TreeNode<TKey, TValue> node) {
        // this comparison is redundant is some cases, because we might already know the result.
        // still, it's O(1) comparisons per level.
        TKey key = node.key1;
        final int comparison = key.compareTo(key1);
        if (comparison == 0)
            throw new RuntimeException("Not supposed to add a third child in this case");

        if (comparison < 0) {
            setRight(middle);
            this.key2 = key1;

            setMiddle(node);
            key1 = key;
            return;
        }

        // comparison > 0, key > key1
        setRight(node);
        this.key2 = key;
    }

    void setRight(TreeNode<TKey, TValue> node) {
        this.right = node;
        if (node != null)
            node.parent = this;
    }

    private TreeNode<TKey, TValue> newTreeNode(TKey key, TValue value) {
        return new TreeNode<TKey, TValue>(key, value);
    }

    public int size() {
        int size = 0;
        if (value != null)
            size = 1;
        if (left != null)
            size += left.size();
        if (middle != null)
            size += middle.size();
        if (right != null)
            size += right.size();
        return size;
    }

    Pair<TKey, TValue> toPair() {
        return new Pair<TKey, TValue>(key1, value);
    }

    public TreeNode<TKey, TValue> findNodeGreaterThan(TKey key) {
        int comparison1 = key.compareTo(key1);
        if (comparison1 < 0) {
            return left.findNodeGreaterThan(key);
        }
        if (comparison1 > 0) {
            int comparison2 = key.compareTo(key2);
            if (comparison2 >= 0)
                return right.findNodeGreaterThan(key);
            return middle.findNodeGreaterThan(key);
        }
        assertEquals(0, comparison1);

        if (value == null) {
            return middle.findNodeGreaterThan(key);
        }

        TreeNode<TKey, TValue> current = this.parent;
        TreeNode<TKey, TValue> prev = this;
        while (current != null) {
            if (prev == current.left) {
                if (current.middle != null) {
                    return current.middle.findNodeGreaterThan(key);
                }
                prev = current;
                current = current.parent;
                continue;
            }
            if (prev == current.middle) {
                if (current.right != null) {
                    return current.right.findNodeGreaterThan(key);
                }
                prev = current;
                current = current.parent;
                continue;
            }
            if (prev == current.right) {
                prev = current;
                current = current.parent;
                continue;
            }
            throw new RuntimeException("Shouldn't reach here");
        }
        assertNull(current);
        return null;
    }

    /**
     * Validate this is a legitimate B-Tree.
     */
    void validate() {
        validateHeight();
        if (value != null) {
            // leaf
            assertNull(left);
            assertNull(middle);
            assertNull(right);
            assertNull(key2);
            assertNotNull(key1);
            return;
        }

        assertNotNull(key1);
        left.validateBounds(null, key1);
        middle.validateBounds(key1, key2);
        if (key2 != null) {
            assertNotNull(right);
            right.validateBounds(key2, null);
        }

        if (left != null)
            left.validate();
        if (middle != null)
            middle.validate();
        if (right != null)
            right.validate();
    }

    private void validateBounds(TKey min, TKey max) {
        validateKey(min, max, key1);
        validateKey(min, max, key2);
        validateBounds(min, max, left);
        validateBounds(min, max, middle);
        validateBounds(min, max, right);
    }

    private void validateBounds(TKey min, TKey max, TreeNode<TKey, TValue> node) {
        if (node != null)
            node.validateBounds(min, max);
    }

    private void validateKey(Comparable<TKey> min, Comparable<TKey> max, TKey key) {
        if (key == null)
            return;
        if (min != null) {
            assertTrue(min.compareTo(key) <= 0);
        }
        if (max != null)
            assertTrue(max.compareTo(key) > 0);
    }

    private void validateHeight() {
        if (left == null)
            return;

        int leftHeight = left.height();

        // let's assert height is the same in all directions
        int middleHeight = middle.height();
        assertEquals(leftHeight, middleHeight);

        if (right != null) {
            int rightHeight = right.height();
            assertEquals(leftHeight, rightHeight);
        }
    }
}
