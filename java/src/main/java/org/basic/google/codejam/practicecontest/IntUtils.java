package org.basic.google.codejam.practicecontest;

import java.math.BigInteger;

public class IntUtils {

    /**
     * Return x / y % mod
     */
    public static int divideMod(int x, int y, int mod) {
        int freeNodesInverse= new BigInteger(Integer.toString(y)).modInverse(new BigInteger(Integer.toString(mod))).intValue();
        return x * freeNodesInverse % mod;
    }
}
