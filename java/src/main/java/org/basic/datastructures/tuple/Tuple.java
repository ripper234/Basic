package org.basic.datastructures.tuple;

/**
 * Tuple are immutable objects.  Tuples should contain only immutable objects or
 * objects that won't be modified while part of a tuple.
 */
public interface Tuple {

    public TupleType getType();
    public int size();
    public <T> T getNthValue(int i);
}