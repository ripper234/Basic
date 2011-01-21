package org.basic.utils;

public class MathUtils {
    public final static double EPSILON = 0.000000001;

    private MathUtils(){}

    public static int pow2(int k) {
        if (k == 0)
            return 1;
        return 2 * pow2(k - 1);
    }

    public static int log(int n) {
        if (n == 1)
            return 0;
        return log(n / 2) + 1;
    }

    public static boolean areEqual(double x, double y) {
        return isZero(x - y);
    }

    public static boolean isZero(double x) {
        return Math.abs(x) < EPSILON;
    }
}
