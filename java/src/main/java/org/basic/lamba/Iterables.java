package org.basic.lamba;

public class Iterables{
    private Iterables() {}

    public static <T> int sum(Iterable<T> iterable, Func<T, Integer> func) {
        int result = 0;
        for (T item : iterable)
            result += func.run(item);
        return result;
    }
}
