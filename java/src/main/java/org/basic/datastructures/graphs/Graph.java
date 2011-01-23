package org.basic.datastructures.graphs;

import com.google.common.base.Function;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Collections2;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;

public class Graph {
    private final List<Edge> edgeList = newArrayList();
    private final Set<Integer> nodes = newHashSet();
    Multimap<Integer, Edge> outgoing = ArrayListMultimap.create();
    Multimap<Integer, Edge> incoming = ArrayListMultimap.create();

    public void addSymmetric(Edge e) {
        addOneWay(e);
        addOneWay(e.opposite());
    }

    public void addOneWay(Edge e) {
        edgeList.add(e);
        outgoing.put(e.u, e);
        incoming.put(e.v, e);
        nodes.add(e.u);
        nodes.add(e.v);
    }

    public boolean hasEdge(int u, int v) {
        return outgoing.containsEntry(u, v);
    }

    public Collection<Edge> getOutgoingEdges(int u) {
        return outgoing.get(u);
    }

    public Collection<Edge> getIncomingEdges(int v) {
        return incoming.get(v);
    }

    public Collection<Integer> getOutgoingNodes(int u) {
        final Collection<Edge> outgoingEdges = getOutgoingEdges(u);
        return Collections2.transform(outgoingEdges, new Function<Edge, Integer>() {
            public Integer apply(Edge edge) {
                return edge.v;
            }
        });
    }

    public List<Edge> getEdges() {
        return edgeList;
    }

    public Set<Integer> getNodes() {
        return nodes;
    }


}
