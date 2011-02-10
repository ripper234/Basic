package org.basic.quora;

import org.apache.commons.lang.time.StopWatch;
import org.basic.datastructures.Pair;
import org.basic.datastructures.geometry.Point;
import org.basic.google.codejam.practiceproblems.alwaysturnleft.Direction;

import java.util.BitSet;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.google.common.collect.Maps.newHashMap;

public class ChallengeOne {
    private final int[][] grid;
    private final Map<Pair<BitSet, Point>, Long> cache = newHashMap();
    private final Point start;
    private final Point end;
    private final int X;
    private final int Y;
    private final int targets;
    private final StopWatch timer = new StopWatch();

    public ChallengeOne(int[][] grid) {
        this.grid = grid;
        this.Y = grid.length;
        this.X = grid[0].length;
        this.start = find(2);
        this.end = find(3);

        int count = 0;
        for (int y = 0; y < Y; ++y)
            for (int x = 0; x < X; ++x) {
                if (grid[y][x] != 1)
                    count++;
            }
        targets = count;
        timer.start();
        Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("Time: " + timer.getTime() / 1000);
            }
        }, 5, 5, TimeUnit.SECONDS);
    }

    private Point find(int value) {
        for (int y = 0; y < grid.length; ++y)
            for (int x = 0; x < grid[0].length; ++x) {
                if (grid[y][x] == value)
                    return new Point(x, y);
            }
        throw new RuntimeException("Not found");
    }

    public static long countDuct(int[][] grid) {
        return new ChallengeOne(grid).solve();
    }

    private long solve() {
        BitSet initial = new BitSet(X * Y);
        initial.set(getIndex(start));

        return solve(initial, start, 1);
    }

    private long solve(BitSet state, Point lastPoint, int length) {
//        final Pair<BitSet, Point> pair = Pair.create(state, lastPoint);
//        final Long cached = cache.get(pair);
//        if (cached != null)
//            return cached;

        if (length > targets) {
            throw new RuntimeException("Shouldn't reach here");
        }
        if (lastPoint.equals(end)) {
            if (length == targets)
                return 1;
            return 0;
        }

        long count = 0;
        for (Direction dir : Direction.values()) {
            Point newPoint = getNewPoint(lastPoint, dir);

            final int newPointIndex = getIndex(newPoint);
            if (isOk(state, newPoint, newPointIndex))
                continue;

            boolean left = check(state, newPoint, Direction.Left);
            boolean right = check(state, newPoint, Direction.Right);
            boolean down = check(state, newPoint, Direction.Down);
            boolean up = check(state, newPoint, Direction.Up);

            if ((left && right && !up && !down) ||
                    (!left && !right && up && down)) {
                // no way to finish this
                continue;
            }

            BitSet newBitSet = new BitSet(X * Y);
            newBitSet.or(state);
            newBitSet.set(newPointIndex);
            count += solve(newBitSet, newPoint, length + 1);
        }
//        if (cache.size() < 9000000) {
//            cache.put(pair, count);
//            if (cache.size() % 1000 == 0)
//                System.out.println(cache.size());
//        }
        return count;
    }

    private boolean check(BitSet state, Point newPoint, final Direction direction) {
        return isOk(state, getNewPoint(newPoint, direction), getIndex(getNewPoint(newPoint, direction)));
    }

    private Point getNewPoint(Point lastPoint, Direction dir) {
        Point newPoint = new Point(lastPoint);
        newPoint.x += dir.dx;
        newPoint.y += dir.dy;
        return newPoint;
    }

    private boolean isOk(BitSet state, Point newPoint, int bitIndex) {
        return !isLegit(newPoint) || grid[newPoint.y][newPoint.x] == 1 || state.get(bitIndex);
    }

    private boolean isLegit(Point point) {
        return point.x >= 0 && point.x < X &&
                point.y >= 0 && point.y < Y;
    }

    private int getIndex(Point point) {
        return point.x + X * point.y;
    }
}
