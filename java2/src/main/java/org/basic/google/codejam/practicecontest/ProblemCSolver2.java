package org.basic.google.codejam.practicecontest;

import org.basic.datastrutcures.graphs.Edge;
import org.basic.datastrutcures.graphs.Graph;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ProblemCSolver2 {
    private final Graph graph = new Graph();

    public ProblemCSolver2(List<Edge> forbiddenEdges) {
        for (Edge e : forbiddenEdges) {
            graph.add(e);
        }
    }

    public static int solve(int n, List<Edge> forbiddenEdges) {
        return new ProblemCSolver2(forbiddenEdges).solveImpl(n);
    }

    private int solveImpl(int n) {
        List<Integer> toPass = newArrayList();
        for (int i = 2; i <= n; ++i)
            toPass.add(i);
        return solveImpl(1, toPass, 1);
    }

    private int solveImpl(int from, List<Integer> toPass, int to) {
        return 0;
    }
}
