package org.basic.riddles;

import java.util.List;

public class Riddles {
    public static int findMaxAscendingList(List<Integer> list) {
        if (list.isEmpty())
            return 0;

        int lastVal = list.get(0);
        int maxLength = 1;
        int currentLength = 1;
        for (int i = 1; i < list.size(); ++i) {
            int current = list.get(i);
            if (current >= lastVal) {
                currentLength++;
                if (currentLength > maxLength)
                    maxLength = currentLength;
            } else
                currentLength = 1;
            lastVal = current;
        }
        return maxLength;
    }

}

