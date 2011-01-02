package datastrutcures.hashmaps;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class HashMap<TKey extends Comparable<TKey>, TValue> implements IHashMap<TKey, TValue> {
    private int size;

    public HashMap(int n) {
        mainList = new ArrayList<List<Pair<TKey,TValue>>>(n);
        for (int i = 0; i < n; ++i)
            mainList.add(null);
    }
    
    public HashMap() {
        this(1024);
    }
    
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

    private List<List<Pair<TKey, TValue>>> mainList;

    public void put(final TKey key, TValue value) {
        List<Pair<TKey, TValue>> list = findListFor(key);
        Pair<TKey, TValue> existing = findInList(key, list);
        if (existing != null) {
            existing.setValue(value);
            return;
        }

        ++size;
        list.add(new Pair<TKey, TValue>(key, value));
    }

    public TValue get(TKey key) {
        List<Pair<TKey, TValue>> list = findListFor(key);
        Pair<TKey, TValue> existing = findInList(key, list);
        if (existing == null)
            return null;

        return existing.getValue();
    }

    public boolean delete(TKey key) {
        List<Pair<TKey, TValue>> list = findListFor(key);
        int index = findIndex(key, list);
        if (index == -1)
            return false;

        list.remove(index);
        --size;
        assertTrue(size >= 0);
        return true;
    }

    public void clear() {
        size = 0;
        mainList.clear();
    }

    public int size() {
        return size;
    }

    private List<Pair<TKey, TValue>> findListFor(TKey key) {
        final int index = key.hashCode() % mainList.size();
        if (mainList.get(index) != null)
            return mainList.get(index);
        
        final ArrayList<Pair<TKey, TValue>> newList = new ArrayList<Pair<TKey, TValue>>();
        mainList.set(index, newList);
        return newList;
    }

    private Pair<TKey, TValue> findInList(final TKey key, List<Pair<TKey, TValue>> list) {
        int index = findIndex(key, list);
        if (index == -1)
        return null;
        return list.get(index);
    }

    private int findIndex(final TKey key, List<Pair<TKey, TValue>> list) {
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getKey().equals(key)) {
                return i;
            }
        }
        return -1;
    }
}

