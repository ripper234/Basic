package org.basic.google.codejam.practiceproblems.alwaysturnleft;

import org.basic.datastrctures.Point;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class ProblemBSolver {
    public int x = 0;
    public int y = 0;
    public final Map<Point, Map<Direction, Boolean>> terrain = newHashMap();
    public int minX = 0;
    public int maxX = 0;
    public int minY = 0;
    public Direction currentDirection = Direction.Down;

    public static String reconstructMaze(String straight, String opposite) {
        return new ProblemBSolver().solve(straight.toCharArray(), opposite.toCharArray());
    }

    enum Direction {
        Left(-1, 0),
        Right(1, 0),
        Up(0, 1),
        Down(0, -1);
        public final int dx;
        public final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    private String solve(char[] straight, char[] opposite) {
        walk(straight);
        currentDirection = turnLeft(turnLeft(currentDirection));
        walk(opposite);

        return printLearnedMaze();
    }

    private void walk(char[] path) {
        for (int i = 0, straightLength = path.length; i < straightLength; i++) {
            char direction = path[i];
            Point position = new Point(x, y);
            Map<Direction, Boolean> square = terrain.get(position);
            if (square == null) {
                square = newHashMap();
                terrain.put(position, square);
            }

            switch (direction) {
                case 'W':
                    square.put(currentDirection, true);
                    x += currentDirection.dx;
                    y += currentDirection.dy;
//                    System.out.println(String.format("(%d, %d)", x, y));
                    break;

                case 'L':
                    //square.put(currentDirection, false);
                    currentDirection = turnLeft(currentDirection);
                    break;

                case 'R':
                    //square.put(currentDirection, false);
                    //square.put(turnLeft(currentDirection), false);
                    currentDirection = turnRight(currentDirection);
                    break;
            }

            if (i < path.length - 1) {
                // don't count the last square in the maze's dimensions
                minX = Math.min(x, minX);
                maxX = Math.max(x, maxX);
                minY = Math.min(y, minY);
            }
        }
    }

    private String printLearnedMaze() {
        StringBuilder builder = new StringBuilder();
        builder.append("\r\n");
        for (int yy = -1; yy >= minY; --yy) {
            for (int xx = minX; xx <= maxX; ++xx) {
                try {
                    Map<Direction, Boolean> p = this.terrain.get(new Point(xx, yy));
                    int encoded = encode(p);
                    String hex = Integer.toHexString(encoded);
                    builder.append(hex);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to encode (" + xx + ", " + yy + ")");
                }
            }
            if (yy != minY)
                builder.append("\r\n");
        }
        return builder.toString();
    }

    private int encode(Map<Direction, Boolean> p) {
        int encoded = 0;
        if (canWalk(p, Direction.Up))
            encoded += 1;
        if (canWalk(p, Direction.Down))
            encoded += 2;
        if (canWalk(p, Direction.Left))
            encoded += 4;
        if (canWalk(p, Direction.Right))
            encoded += 8;
        return encoded;
    }

    private static boolean canWalk(Map<Direction, Boolean> p, Direction direction) {
        final Boolean result = p.get(direction);
        // if we don't know, assume we can't
        return result != null && result;
    }

    private static Direction turnRight(Direction current) {
        return turnLeft(turnLeft(turnLeft(current)));
    }

    private static Direction turnLeft(Direction current) {
        switch (current) {
            case Up:
                return Direction.Left;

            case Left:
                return Direction.Down;

            case Down:
                return Direction.Right;

            case Right:
                return Direction.Up;
        }

        throw new RuntimeException("Shouldn't happen");
    }
}
