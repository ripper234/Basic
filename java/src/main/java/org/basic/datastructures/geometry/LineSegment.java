package org.basic.datastructures.geometry;

import static org.basic.utils.MathUtils.isZero;

@SuppressWarnings({"SuspiciousNameCombination"})
public class LineSegment {
    private final Point a;
    private final Point b;

    public LineSegment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public double length() {
        return a.distance(b);
    }

    public Double incline() {
        double dx = b.x - a.x;
        if (isZero(dx))
            return null;

        double dy = b.y - a.y;
        return dy / dx;
    }
}
