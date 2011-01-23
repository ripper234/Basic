package org.basic.google.codejam.practicecontest;

import org.basic.datastructures.Pair;
import org.basic.datastructures.graphs.Edge;
import org.basic.datastructures.graphs.Graph;
import org.basic.datastructures.tuple.Tuple;
import org.basic.datastructures.tuple.TupleType;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

public class ProblemCSolver2 {
    @SuppressWarnings({"FieldCanBeLocal"})
    private final int mod = 9901;
    private final Graph graph = new Graph();
    private final static TupleType tupleType = TupleType.DefaultFactory.create(Integer.class, Integer.class, Set.class);
    private Map<Tuple, Integer> tupleCache = newHashMap();
    private Map<Pair<Integer, Boolean>, Integer> freeCache = newHashMap();
    private final Set<Integer> forbiddenNodes = newHashSet();

    public ProblemCSolver2(List<Edge> forbiddenEdges) {
        for (Edge e : forbiddenEdges) {
            graph.addSymmetric(e);
            forbiddenNodes.add(e.u);
            forbiddenNodes.add(e.v);
        }
    }

    public static int solve(int n, List<Edge> forbiddenEdges) {
        return new ProblemCSolver2(forbiddenEdges).solveImpl(n);
    }

    private int solveImpl(int n) {
        List<Integer> toPass = newArrayList();
        for (int i = 2; i <= n; ++i)
            toPass.add(i);
        int val = solveImpl(1, new HashSet<Integer>(toPass), 1);
        return IntUtils.divideMod(val, 2, mod);
    }

    private int solveImpl(int from, Set<Integer> toPass, int to) {
        if (!areForbidden(toPass, from, to))
            return solveWithoutForbiddenNodes(from, toPass.size(), to);

        if (toPass.isEmpty()) {
            if (graph.hasEdge(from, to))
                return 0;
            return 1;
        }

        Tuple tuple = tupleType.createTuple(from, to, toPass);
        Integer solution = tupleCache.get(tuple);
        if (solution != null)
            return solution;

        int sum = 0;
        for (Integer x : toPass) {
            if (graph.hasEdge(from, x))
                continue;

            HashSet<Integer> newSet = new HashSet<Integer>(toPass);
            newSet.remove(x);
            sum += solveImpl(x, newSet, to);
            sum %= mod;
        }

        tupleCache.put(tuple, sum);
        return sum;
    }

    private int solveWithoutForbiddenNodes(int from, int n, int to) {
        Pair<Integer, Boolean> pair = new Pair<Integer, Boolean>(n, from == to);

        return solveWithoutForbiddenNodes(n, pair);
    }

    private int solveWithoutForbiddenNodes(int n, Pair<Integer, Boolean> pair) {
        if (n == 0) {
            if (pair.getValue())
                return 0;
            return 1;
        }

        Integer cached = freeCache.get(pair);
        if (cached != null)
            return cached;

        int result = n * solveWithoutForbiddenNodes(n - 1, pair) % mod;
        freeCache.put(pair, result);
        return result;
    }

    private boolean areForbidden(Set<Integer> toPass, int from, int to) {
        for (Integer v : toPass) {
            if (forbiddenNodes.contains(v))
                return true;
        }
        return forbiddenNodes.contains(from) || forbiddenNodes.contains(to);
    }
}
