package org.basic.riddles.ihasleetcodes;

/**
 * http://www.ihas1337code.com/2011/02/coins-in-line.html
 */
public class BestSumGame {
    private final int[] arr;
    private final int[][] sums;

    public BestSumGame(int[] arr) {
        this.arr = arr;
        sums = new int[arr.length][arr.length];

        for (int a = 0; a < arr.length; ++a)
            for (int b = a; b < arr.length; ++b) {
                if (a == b) {
                    sums[a][b] = arr[a];
                    continue;
                }
                int s1 = arr[a] - sums[a + 1][b];
                int s2 = arr[b] - sums[a][b - 1];
                sums[a][b] = Math.max(s1, s2);
            }
    }

    public int getBestMove(int a, int b) {
        if (a == b)
            return a;

        int s1 = arr[a] - sums[(a + 1)][b];
        int s2 = arr[b] - sums[a][(b - 1)];
        if (s1 > s2)
            return a;
        return b;
    }

}
