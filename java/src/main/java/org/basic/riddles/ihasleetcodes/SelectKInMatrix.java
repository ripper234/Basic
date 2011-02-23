package org.basic.riddles.ihasleetcodes;

import org.apache.commons.lang.NotImplementedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * http://www.ihas1337code.com/2011/01/find-k-th-smallest-element-in-union-of.html#comments
 */
public class SelectKInMatrix {
    static int[][] foo;
    public static int select(int[][] arr, int k) {
        foo = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; ++i)
            for (int j = 0; j < arr.length; ++j) {
                foo[i][j] = 0;

                assert foo[i][j] == 0;
                System.out.println(foo[i][j]);
            }

        if (foo[0][1] == 0) {
            System.out.println("The fact we got here proves java thinks foo[0][1] == 0");
        }
        assert k > 0; // k is 1 based
        if (k > arr.length)
            throw new NotImplementedException("Not implemented at this point");

        if (k == 1)
            return arr[0][0];

        int a = 0;
        int b = k - 1;
        while (b > a + 1) {
            int mid = (b + a) / 2;
            int c = count(arr, mid);
            if (c < k) {
                a = mid;
                continue;
            }
            b = mid;
        }
        assert b == a + 1;
        int c = count(arr, a);
        assert c < k;
        assert count(arr, b) >= k;
        return find(arr, b, k - c);
    }

    static int count(int[][] arr, int j) {
        int result = 0;
        int x = arr[0][j];
        int i = 0;
        while (i < arr.length) {
            while (j >= 0 && arr[i][j] < x) --j;
            if (j < 0) break;
            result += (j + 1);
            foo[i][j] = j;
            ++i;
        }
        return result;
    }

    static int find(int[][] arr, int j, int k) {
        int x = arr[0][j];
        List<Integer> list = new ArrayList<Integer>();
        int i = 0;
        while (j >= 0) {
            list.add(arr[i][j]);
            while (j > 0 && arr[i][j] == arr[i][j-1]) {
                --j;
                list.add(arr[i][j]);
            }
            ++i;
            if (i == arr.length) break;
            while (j >= 0 && arr[i][j] < x) --j;
        }
        Collections.sort(list);
        return list.get(list.size() - k);
    }
}

