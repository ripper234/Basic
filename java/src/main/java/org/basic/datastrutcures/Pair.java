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
}
