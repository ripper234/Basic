package org.basic.datastrutcures.tuple;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class TupleTester {
    public static TupleType tripletTupleType = TupleType.DefaultFactory.create(
            Number.class,
            String.class,
            Character.class);
    public static TupleType emptyTupleType = TupleType.DefaultFactory.create();

    @Test
    public void one() {
        final Tuple t1 = tripletTupleType.createTuple(1, "one", 'a');
        System.out.println("t1 = " + t1);
    }

    @Test
    public void two() {
        final Tuple t2 = tripletTupleType.createTuple(2l, "two", 'b');
        System.out.println("t2 = " + t2);
    }

    @Test
    public void three() {
        final Tuple t3 = tripletTupleType.createTuple(3f, "three", 'c');
        System.out.println("t3 = " + t3);
    }

    @Test
    public void nullTuple() {
        final Tuple tnull = tripletTupleType.createTuple(null, "(null)", null);
        System.out.println("tnull = " + tnull);
    }

    @Test
    public void empty() {
        final Tuple tempty = emptyTupleType.createTuple();
        System.out.println("\ntempty = " + tempty);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void wrongTypes() {
        tripletTupleType.createTuple(1, 2, 3);

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void wrongNumberOfArguments() {
        emptyTupleType.createTuple(1);
    }

    @Test
    public void valueType() {
        final Tuple t9 = tripletTupleType.createTuple(9, "nine", 'i');
        assertEquals(Integer.class, t9.getNthValue(0).getClass());
    }
}
