package org.basic.google.codejam.contest2008beta;

import org.basic.datastructures.Pair;
import org.basic.datastructures.graphs.Edge;
import org.basic.datastructures.graphs.Graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

import static com.google.common.collect.Maps.newHashMap;

public class DijkstraRunner {
    private Map<Integer, Integer> distances;
    private final Map<Integer, Integer> numberOfOptimalPaths = newHashMap();
    private final Graph g;
    private final PriorityQueue<Pair<Edge, Integer>> queue = new PriorityQueue<Pair<Edge, Integer>>(10, new Comparator<Pair<Edge, Integer>>() {
        public int compare(Pair<Edge, Integer> p1, Pair<Edge, Integer> p2) {
            return p1.getValue() - p2.getValue();
        }
    });

    public DijkstraRunner(Graph g) {
        this.g = g;
    }

    public Map<Integer, Integer> run(int start) {
        distances = newHashMap();
        distances.put(start, 0);
        numberOfOptimalPaths.put(start, 1);

        for (Edge e : g.getOutgoingEdges(start)) {
            queue.add(newPair(e, e.weight));
        }
        processQueue();
        return distances;
    }

    private static Pair<Edge, Integer> newPair(Edge e, int i) {
        return new Pair<Edge, Integer>(e, i);
    }

    private void processQueue() {
        while (!queue.isEmpty()) {
            // get the minimal edge
            final Pair<Edge, Integer> pair = queue.poll();
            Edge e = pair.getKey();


            int u = e.u;
            int v = e.v;

            int uDistance = distances.get(u);
            Integer currentDistance = distances.get(v);
            int newDistance = uDistance + e.weight;
            if (currentDistance == null || newDistance < currentDistance) {
                numberOfOptimalPaths.put(v, numberOfOptimalPaths.get(u));
                distances.put(v, newDistance);
                Collection<Edge> edges = g.getOutgoingEdges(v);
                for (Edge e1 : edges) {
                    queue.add(newPair(e1, e1.weight + newDistance));
                }
            } else if (currentDistance == newDistance) {
                // found more optimal paths to the same node
                numberOfOptimalPaths.put(v, numberOfOptimalPaths.get(u) + numberOfOptimalPaths.get(v));
            }
        }
    }

    public Map<Integer, Integer> getNumberOfOptimalPaths() {
        return numberOfOptimalPaths;
    }
}

