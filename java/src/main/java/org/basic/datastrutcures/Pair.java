package org.basic.datastrutcures;

/**
* Created by IntelliJ IDEA.
* User: ron
* Date: Jan 3, 2011
* Time: 8:16:29 AM
* To change this template use File | Settings | File Templates.
*/
public class Pair<TKey, TValue> {
    private TKey key;
    private TValue value;

    public Pair(TKey key, TValue value) {

        this.key = key;
        this.value = value;
    }

    public TKey getKey() {
        return key;
    }

    public TValue getValue() {
        return value;
    }

    public void setValue(TValue value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
        if (value != null ? !value.equals(pair.value) : pair.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
