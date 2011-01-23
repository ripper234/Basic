package org.basic.google.codejam.contest2008beta;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.basic.datastructures.graphs.Edge;
import org.basic.datastructures.graphs.Graph;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;

public class RandomRoute {
    private final Graph g;
    private Map<Integer, Integer> distances;
    private final Map<Edge, Double> edgeChance = newHashMap();
    private final Map<Integer, Double> nodeChance = newHashMap();
    private final Map<Integer, Integer> numberOfOptimalPaths;

    private int reachables;

    public RandomRoute(Graph g, final int startNodeId) {
        this.g = g;

        final DijkstraRunner dijkstraRunner = new DijkstraRunner(g);
        distances = dijkstraRunner.run(startNodeId);
        numberOfOptimalPaths = dijkstraRunner.getNumberOfOptimalPaths();

        reachables = 0;
        for (int node : g.getNodes()) {
            if (node != startNodeId && distances.get(node) != null)
                reachables++;
        }
    }

    public static List<Double> getProbabilities(Graph g, int startNodeId) {
        return new RandomRoute(g, startNodeId).solve();
    }

    private List<Double> solve() {
        List<Double> chances = newArrayList();
        for (Edge e : g.getEdges()) {
            double chance = getChance(e);
            chances.add(chance);
        }
        return chances;
    }

    private double getChance(Edge e) {
        Double chance = edgeChance.get(e);
        if (chance != null)
            return chance;

        int v = e.v;
        int u = e.u;

        if (!isOptimalEdge(e))
            return 0;

        int optimalsV = numberOfOptimalPaths.get(v);
        int optimalsU = numberOfOptimalPaths.get(u);

        double vChance = getChance(v);
        double result = vChance * optimalsU / optimalsV;
        edgeChance.put(e, result);
        return result;
    }

    double getChance(int node) {
        Double savedChance = nodeChance.get(node);
        if (savedChance != null)
            return savedChance;

        Collection<Edge> outgoing = filterOptimals(g.getOutgoingEdges(node));
        double chance = 1D / reachables;
        for (Edge e : outgoing) {
            chance += getChance(e);
        }
        nodeChance.put(node, chance);
        return chance;
    }

    private Collection<Edge> filterOptimals(Collection<Edge> edges) {
        return Collections2.filter(edges, new Predicate<Edge>() {
            public boolean apply(Edge input) {
                return isOptimalEdge(input);
            }
        });
    }

    private boolean isOptimalEdge(Edge e) {
        final Integer du = distances.get(e.u);
        if (du == null)
        {
            // u isn't reachable
            return false;
        }

        final int dv = distances.get(e.v);

        return dv == du+ e.weight;
    }

}

