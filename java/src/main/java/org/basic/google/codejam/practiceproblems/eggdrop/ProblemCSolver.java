package org.basic.google.codejam.practiceproblems.eggdrop;

public class ProblemCSolver {
    public String solve(int floors, int dropped, int broken) {
        return "a";
    }

    private boolean solvable(int floors, int dropped, int broken) {
        // f(d, 0) = 0
        // f(d, 1) = d
        // f(d, 2) = sigma(1..d)(d+1-i) = sigma(1..d)(i) = d*(d+1)/2

        // f(d, b) = sigma(1..d, 1+f(d-i, b-1))
        //         = sigma(1..d, 1+f(i, b-1))
        //         = d + sigma(1..d, f(i, b-1))
        //         = d + f(1, b-1) + f(2, b-1) ... + f(d, b-1)
        //         =
        return true;
    }
}
