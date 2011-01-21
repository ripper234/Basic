package org.basic.google.codejam.practicecontest;

import ch.lambdaj.Lambda;
import org.basic.datastructures.geometry.Point;
import org.hamcrest.Description;
import org.junit.internal.matchers.TypeSafeMatcher;

import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

public class ProblemBSolver1 {
    public static int solve(int k, List<Point> points) {
        List<Integer> distances = getDists(points);
        distances = filterDups(distances);
        Collections.sort(distances);
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x - o2.x;
            }
        });
        int index = binarySearch(k, distances, points);
        if (index == -1)
            throw new RuntimeException("Didn't find any distance");

        return distances.get(index);

    }

    private static int binarySearch(int k, List<Integer> distances, List<Point> points) {
        if (distances.size() == 0)
            return -1;

        int index = distances.size() / 2;
        int side = distances.get(index);
        if (canCover(k, side, points)) {
            if (index == 0)
                return 0;

            int subIndex = binarySearch(k, distances.subList(0, index), points);
            if (subIndex != -1)
                return subIndex;
            else
                return index;
        }
        int rest = binarySearch(k, distances.subList(index + 1, distances.size()), points);
        if (rest == -1)
            return -1;

        return index + 1 + rest;
    }

    /**
     * Generate a list of all candidates to place square corners at.
     *
     * @param points
     * @return
     */
    private static List<Point> generateCandidates(List<Point> points, int side) {
        Set<Point> candidates = new HashSet<Point>();
        for (Point point1 : points)
            for (Point point2 : points) {
                if (Math.abs(point2.x - point1.x) > side)
                    continue;
                if (Math.abs(point2.y - point2.y) > side)
                    continue;
                Point candidate = new Point(point1.x, point2.y);
                candidates.add(candidate);
            }
        return newArrayList(candidates);
    }

    private static boolean canCover(int k, int side, List<Point> points) {
        if (k == 0) {
            return points.isEmpty();
        }
        if (points.size() == 0)
            return true;

        Point first = points.get(0);
        List<Point> near = getPointsRightOf(first, side, points);
        Collections.sort(near, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.y - o2.y;
            }
        });

        for (Point point : points) {
            if (point.y > first.y)
                continue;

            List<Point> left = removePointsCovered(new Point(first.x, point.y), side, points);
            if (canCover(k-1, side, left))
                return true;
        }
        return false;
    }

    private static List<Point> removePointsCovered(Point location, int side, List<Point> points) {
        List<Point> result = newArrayList();
        for (Point point : points)
        {
            if (!(point.x - location.x >= 0 && point.x - location.x <= side &&
                    point.y - location.y >= 0 && point.y - location.y <= side))
                result.add(point);

        }
        return result;
    }

    private static List<Point> getPointsRightOf(final Point current, final int side, List<Point> points) {
        TypeSafeMatcher<Point> matcher = new TypeSafeMatcher<Point>() {
            @Override
            public boolean matchesSafely(Point o) {
                return o.x - current.x >= 0 && o.x - current.x <= side;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
        return Lambda.filter(matcher, points);
    }

    private static boolean coveredByBox(Corner corner, Point cornerOfBox, Point point, Integer side) {
        int dx = cornerOfBox.x - point.x;
        int dy = cornerOfBox.y - point.y;

        dx *= corner.dx;
        dy *= corner.dy;

        return dx >= 0 && dx <= side && dy >= 0 && dy <= side;
    }

    private static List<Integer> filterDups(List<Integer> distances) {
        return newArrayList(Lambda.selectDistinct(distances));
    }

    private static List<Integer> getDists(List<Point> points) {
        List<Integer> distances = newArrayList();
        for (int i = 0; i < points.size(); ++i)
            for (int j = i + 1; j < points.size(); ++j) {
                addDistance(distances, points.get(i).x, points.get(j).x);
                addDistance(distances, points.get(i).y, points.get(j).y);
            }
        return distances;
    }

    private static void addDistance(List<Integer> distances, int x1, int x2) {
        int distX = Math.abs(x1 - x2);
        if (distX > 0)
            distances.add(distX);
    }
}

