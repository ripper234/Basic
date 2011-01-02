package datastrutcures.linkedlists;

public class LinkedListNode<T> {
    LinkedListNode<T> prev;
    LinkedListNode<T> next;
    boolean dummy;

    T item;

    public LinkedListNode(T item) {
        this.item = item;
        this.dummy = false;
    }
    
    public LinkedListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(LinkedListNode<T> prev) {
        this.prev = prev;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
