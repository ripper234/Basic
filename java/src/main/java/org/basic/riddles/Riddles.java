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

        int k = log(n) - 1;
        // 2^k <= n-1 <= 2^(k+1)
        int left;
        int right;
        if (n - 1 <= pow2(k + 1) - 1 + pow2(k) - 1) {
            right = pow2(k) - 1;
            left = n - 1 - right;
        } else {
            left = pow2(k + 1) - 1;
            right = n - 1 - left;
        }

        Tree result = new Tree();
        result.left = buildNearCompleteTree(left);
        result.right = buildNearCompleteTree(right);
        return result;
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

