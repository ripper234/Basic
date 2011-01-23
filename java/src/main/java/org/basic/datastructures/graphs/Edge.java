package org.basic.datastructures.graphs;

public class Edge {
    public final int u;
    public final int v;
    public final int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public Edge(int u, int v) {
        this(u, v, 0);
    }

    public Edge opposite() {
        return new Edge(v, u, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return u == edge.u && v == edge.v;

    }

    @Override
    public int hashCode() {
        int result = u;
        result = 31 * result + v;
        return result;
    }
}
