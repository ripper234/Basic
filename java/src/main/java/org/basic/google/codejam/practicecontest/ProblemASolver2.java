package org.basic.google.codejam.practicecontest;

public class ProblemASolver2 {
    public static String staticSolve(long white, long black) {
        if ((white * 2 + black) % 2 == 1)
            return "BLACK";
        return "WHITE";
    }
}

