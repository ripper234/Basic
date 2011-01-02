package datastrutcures;

import java.util.Iterator;
import java.util.List;

public class BTree<T extends Comparable<T>> implements ITree<T> {
    public void add(Iterable<T> items) {
        for (T item : items)
            add(item);
    }

    public void add(T item) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean delete(T item) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean exists(T item) {
        return false;
    }

    public int height() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int size() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<T> toList() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterator<T> iterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

   

}
