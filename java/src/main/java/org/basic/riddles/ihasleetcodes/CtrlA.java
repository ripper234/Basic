package org.basic.riddles.ihasleetcodes;

import static java.lang.Math.max;
import static java.lang.System.arraycopy;

public class CtrlA {
    public static int solve(int m) {
        // We used two bounded arrays.
        // 'past' will keep the best value we can achieve
        // 'ctrlv' will keep the best value we can achieve, given that the last action is ctrl-v
        // we only need to keep last 4 positions
        int[] past = new int[4];
        // let's manually fill this in for small values.
        past[1] = 1;
        past[2] = 2;
        past[3] = 3;
        int[] ctrlv = new int[4];


        if (m <= 3)
            return past[m];

        for (int i = 4; i <= m; ++i) {
            int clickA = past[3] + 1;
            int firstCtrlV = past[0] * 2; // this means we're pressing ctrl-A ctrl-C ctrl-V ctrl-V
            int secondCtrlV = ctrlv[3] + (ctrlv[3] - ctrlv[2]);

            int newValue = max(firstCtrlV, max(secondCtrlV, clickA));
            int newCtrlVValue = max(firstCtrlV, secondCtrlV);

            roll(past);
            past[3] = newValue;
            roll(ctrlv);
            ctrlv[3] = newCtrlVValue;
        }
        return past[3];
    }

    private static void roll(int []arr) {
        arraycopy(arr, 1, arr, 0, arr.length - 1);
    }
}
