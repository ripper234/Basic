package org.basic.google.codejam.practiceproblems.alwaysturnleft;

/**
* Created by IntelliJ IDEA.
* User: ron
* Date: Feb 10, 2011
* Time: 5:46:19 PM
* To change this template use File | Settings | File Templates.
*/
public enum Direction {
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
