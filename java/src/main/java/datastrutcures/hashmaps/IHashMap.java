package datastrutcures.hashmaps;

public interface IHashMap<TKey, TValue> {
    void put(TKey key, TValue value);
    TValue get(TKey key);
    boolean delete(TKey key);
    void clear();
    int size();
}
