package org.basic.google.codejam.contest2008;

import org.basic.datastructures.geometry.LineSegment;
import org.basic.datastructures.geometry.Point;
import org.basic.utils.MathUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.Math.pow;

public class Triangle {
    private final Point p1;
    private final Point p2;
    private final Point p3;
    private List<LineSegment> segments = newArrayList();

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

        segments.add(new LineSegment(p1, p2));
        segments.add(new LineSegment(p2, p3));
        segments.add(new LineSegment(p3, p1));
        Collections.sort(segments, new Comparator<LineSegment>() {
            public int compare(LineSegment o1, LineSegment o2) {
                if (MathUtils.areEqual(o1.length(), o2.length()))
                    return 0;

                if (o1.length() < o2.length())
                    return 1;
                
                return -1;
            }
        });
    }

    public static String classify(Point a, Point b, Point c) {
        Triangle triangle = build(a, b, c);
        if (triangle.isNotATriangle())
            return "not a triangle";

        String result = "";
        if (triangle.isIsosceles())
            result += "isosceles ";
        else
            result += "scalene ";

        result += triangle.classifyInternalAngles();
        result += " triangle";
        return result;
    }

    private String classifyInternalAngles() {
        double delta = pow(segments.get(0).length(), 2) -
                pow(segments.get(1).length(), 2) -
                pow(segments.get(2).length(), 2);
        if (MathUtils.isZero(delta))
            return "right ";
        if (delta > 0)
            return "obtuse ";
        return "acute ";

//
//        double alpha = angle(segments.get(0), segments.get(1));
//        double beta = angle(segments.get(1), segments.get(2));
//        double gamma = angle(segments.get(2), segments.get(0));
//
//        if (MathUtils.areEqual(alpha, Math.PI/2) ||
//                MathUtils.areEqual(beta, Math.PI/2) ||
//                MathUtils.areEqual(gamma,Math.PI/2))
//            return "right ";
//
//        if (alpha > Math.PI / 2 ||
//                beta > Math.PI / 2 ||
//                gamma > Math.PI / 2)
//            return "obtuse ";
//
//        return "acute";
    }

    private boolean isNotATriangle() {
        return MathUtils.areEqual(segments.get(0).length(),
                segments.get(1).length() +
                        segments.get(2).length());
    }

    private static Triangle build(Point a, Point b, Point c) {
        return new Triangle(a, b, c);
    }

    public boolean isIsosceles() {
        double l1 = segments.get(0).length();
        double l2 = segments.get(1).length();
        double l3 = segments.get(2).length();

        return MathUtils.areEqual(l1, l2) ||
                MathUtils.areEqual(l2, l3) ||
                MathUtils.areEqual(l1, l3);
    }
}

