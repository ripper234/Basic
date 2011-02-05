package org.basic.algorithms;

import java.util.List;

import static java.lang.Math.max;

public class Knapsack {
    private final List<Integer> wood;
    private final List<Integer> people;
    private final List<Integer> gold;
    private final List<Integer> effectivness;
    private final int N;

    public Knapsack(List<Integer> wood, List<Integer> people, List<Integer> gold, List<Integer> effectivness) {
        N = wood.size();
        if (wood.size() != N ||
                people.size() != N ||
                gold.size() != N)
            throw new RuntimeException("All should have the same length");

        this.wood = wood;
        this.people = people;
        this.gold = gold;
        this.effectivness = effectivness;
    }

    int run(int G, int W, int P) {
        int[][][][] te = new int[G + 1][W + 1][P + 1][N];

        for (int g = 0; g <= G; ++g)
            for (int w = 0; w <= W; ++w)
                for (int p = 0; p <= P; ++p)
                    for (int i = 0; i < N; ++i) {
                        int e = 0;
                        if (i > 0)
                            e = te[g][w][p][i - 1];
                        if (w >= wood.get(i) &&
                                g >= gold.get(i) &&
                                p >= people.get(i)) {
                            e = max(e, te[g - gold.get(i)][w - wood.get(i)][p - people.get(i)][i] +
                                    effectivness.get(i));
                        }

                        te[g][w][p][i] = e;
                    }

        return te[G][W][P][N - 1];
    }
}
