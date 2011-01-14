package org.basic.google.codejam.practicecontest;

public enum Corner {
    TopLeft(1, 1),
    TopRight(1, -1),
    BottomLeft(-1, 1),
    BottomRight(-1, -1);
    public final int dx;
    public final int dy;

    Corner(int dx, int dy) {

        this.dx = dx;
        this.dy = dy;
    }
}
