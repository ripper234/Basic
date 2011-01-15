package org.basic.google.codejam.practiceproblems.eggdrop;

import org.basic.datastructures.Pair;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class ProblemCSolver {
    private long maxDForB2 = -1;

    public String solve(long floors, long dropped, long broken) {
        precalcFValues(dropped, broken);
        return f(dropped, broken) + " " + minD(floors, dropped, broken) + " " + minB(floors, dropped, broken);
    }

    private long minB(long floors, long dropped, long broken) {
        long lowB = 1;
        long highB = broken;

        while (highB > lowB + 1) {
            long midB = (highB + lowB) / 2;
            final long f = f(dropped, midB);
            if (f == -1 || f >= floors) {
                highB = midB;
            } else {
                lowB = midB;
            }
        }
        if (f(dropped, lowB) >= floors)
            return lowB;
        return highB;
    }

    private long minD(long floors, long dropped, long broken) {
        long lowD = 1;
        long highD = dropped;

        while (highD > lowD + 1) {
            long midD = (highD + lowD) / 2;
            final long f = f(midD, broken);
            if (f == -1 || f >= floors) {
                highD = midD;
            } else {
                lowD = midD;
            }
        }
        if (f(lowD, broken) >= floors)
            return lowD;
        return highD;
    }

    private Map<Pair<Long, Long>, Long> fCache = newHashMap();

    void precalcFValues(long maxD, long maxB) {
        for (long d = 1; d <= maxD; ++d) {
            // start from b = 2 instead of 1
            // this single trick saves a lot of time - for b = 1 f is calculated manually anyway
            for (long b = 2; b <= Math.min(maxB, d); ++b) {
                final long f1 = f(d - 1, b - 1);
                final long f2 = f(d - 1, b);

                long result = 1 + f1 + f2;
                if (f1 == -1 || f2 == -1 || result > 4294967296L) {
                    maxBForD.put(d, b);
                    fCache.put(new Pair<Long, Long>(d, b), -1L);
                    if (b == 2) {
                        maxDForB2 = d;
                        return;
                    }
                    break;
                }

                fCache.put(new Pair<Long, Long>(d, b), result);
            }
        }
    }

    private Map<Long, Long> maxBForD = newHashMap();

    long f(long d, long b) {
        if (b == 0 || d == 0)
            return 0;
        if (b == 1)
            return d;
        if (b >= 2 && maxDForB2 != -1 && d >= maxDForB2)
            return -1;
        
        Long maxB = maxBForD.get(d);
        if (maxB != null && b >= maxB)
            return -1;

        if (b > d)
            return f(d, d);

        final Pair<Long, Long> pair = new Pair<Long, Long>(d, b);
        Long cached = fCache.get(pair);
        if (cached == null) {
            throw new RuntimeException("Not calculated");
        }

        return cached;
    }
}
