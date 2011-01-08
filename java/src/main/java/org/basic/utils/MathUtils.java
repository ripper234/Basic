package org.basic.utils;

public class MathUtils {
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
}
