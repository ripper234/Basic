package org.basic.riddles;

import java.util.List;

public class Riddles {
    public static int findMaxAscendingList(List<Integer> list) {
        if (list.isEmpty())
            return 0;

        int lastVal = list.get(0);
        int maxLength = 1;
        int currentLength = 1;
        for (int i = 1; i < list.size(); ++i) {
            int current = list.get(i);
            if (current >= lastVal) {
                currentLength++;
                if (currentLength > maxLength)
                    maxLength = currentLength;
            } else
                currentLength = 1;
            lastVal = current;
        }
        return maxLength;
    }

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
            right = sizeOfCompleteTree(k-2);
            left = n - 1 - right;
        }

        Tree result = new Tree();
        result.left = buildNearCompleteTree(left);
        result.right = buildNearCompleteTree(right);
        return result;
    }

    private static int sizeOfCompleteTree(int k) {
        return pow2(k) - 1;
    }

    private static int pow2(int k) {
        if (k == 0)
            return 1;
        return 2 * pow2(k - 1);
    }

    private static int log(int n) {
        if (n == 1)
            return 0;
        return log(n / 2) + 1;
    }
}

