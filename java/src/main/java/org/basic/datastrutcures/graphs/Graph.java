package org.basic.datastrutcures.graphs;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Graph {
    private final List<Edge> edgeList = newArrayList();
    Multimap<Integer, Integer> edgeMap = ArrayListMultimap.create();

    public void add(Edge e) {
        edgeList.add(e);
        edgeMap.put(e.u, e.v);
        edgeMap.put(e.v, e.u);
    }

    public boolean hasEdge(int u, int v) {
        return edgeMap.containsEntry(u, v);
    }

    public Collection<Integer> getEndpoints(int u) {
        return edgeMap.asMap().get(u);
    }
}
