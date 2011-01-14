package org.basic.google.codejam.practiceproblems.aliennumbers;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class ProblemASolver {
    private final char[] source;
    private final char[] target;

    private final Map<Character, Integer> sourceDigitValue;

    public ProblemASolver(String sourceNumerals, String targetNumerals) {
        source = sourceNumerals.toCharArray();
        sourceDigitValue = newHashMap();
        for (int i = 0; i < source.length; ++i)
            sourceDigitValue.put(source[i], i);
        target = targetNumerals.toCharArray();
    }

    public static String translate(String number, String sourceNumerals, String targetNumerals) {
        return new ProblemASolver(sourceNumerals, targetNumerals).translateImpl(number.toCharArray());
    }

    private String translateImpl(char[] number) {
        long num = 0;
        for (char digitChar : number) {
            num *= getBase();
            num += readDigit(digitChar);
        }

        if (num == 0)
            return "" + target[0];

        StringBuilder result = new StringBuilder();
        while (num > 0) {
            long digit = num % target.length;
            num -= digit;
            num /= target.length;
            final char c = target[(int) digit];
            result.append(c);
        }
        return StringUtils.reverse(result.toString());
    }

    private long readDigit(char c) {
        return sourceDigitValue.get(c);
    }

    private int getBase() {
        return source.length;
    }
}
