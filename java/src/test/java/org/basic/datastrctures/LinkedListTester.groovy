package org.basic.datastrctures

import org.basic.datastructures.linkedlists.MyLinkedList
import org.junit.Test
import org.testng.Assert
import static org.testng.AssertJUnit.assertNull
import static org.testng.AssertJUnit.assertEquals

public class LinkedListTester {
  @Test
  public void insertAfterMiddle() {
    def list = new MyLinkedList<Integer>();
    list.insertLast(1);
    list.insertLast(2);
    list.insertLast(4);

    def node = list.find(2);
    list.insertAfter(3, node);

    Assert.assertEquals([1, 2, 3, 4], list.toList());
  }

  @Test
  public void insertAfterLast() {
    def list = new MyLinkedList<Integer>([1,2,3]);
    def node = list.find(3);
    list.insertAfter(4, node);

    Assert.assertEquals([1, 2, 3, 4], list.toList());
  }

  @Test
  public void insertAfterFirst() {
    def list = new MyLinkedList<Integer>([1,3,4]);
    def node = list.find(1);
    list.insertAfter(2, node);

    Assert.assertEquals([1, 2, 3, 4], list.toList());
  }

  @Test
  public void insertBeforeMiddle() {
    def list = new MyLinkedList<Integer>([1,3,4]);
    def node = list.find(3);
    list.insertBefore(2, node);

    Assert.assertEquals([1, 2, 3, 4], list.toList());
  }

  @Test
  public void insertBeforeLast() {
    def list = new MyLinkedList<Integer>([1,2,4]);

    def node = list.find(4);
    list.insertBefore(3, node);

    Assert.assertEquals([1, 2, 3, 4], list.toList());
  }

  @Test
  public void insertBeforeFirst() {
    def list = new MyLinkedList<Integer>([2, 3, 4]);
    list.insertBefore(1, list.find(2));

    Assert.assertEquals([1, 2, 3, 4], list.toList());
  }
  
  @Test
  public void removeMiddle() {
    def list = new MyLinkedList<Integer>([1, 2, 3, 4]);
    def node = list.find(2);
    list.remove(node);
    Assert.assertEquals([1, 3, 4], list.toList());
  }

  @Test
  public void removeLast() {
    def list = new MyLinkedList<Integer>([1, 2, 3, 4]);
    def node = list.find(4);
    list.remove(node);
    Assert.assertEquals([1, 2, 3], list.toList());
  }

  @Test
  public void removeFirst() {
    def list = new MyLinkedList<Integer>([1, 2, 3, 4]);
    def node = list.find(1);
    list.remove(node);
    Assert.assertEquals([2, 3, 4], list.toList());
  }

  @Test
  public void findNonExisting() {
    def list = new MyLinkedList<Integer>([1, 2, 3, 4]);
    def node = list.find(10);
    assertNull(node);
  }

  @Test
  public void size(){
    def list = new MyLinkedList<Integer>();
    assertEquals 0, list.size()
    list.insertFirst(2);
    assertEquals 1, list.size()
    list.insertFirst(1);
    assertEquals 2, list.size()
    list.insertLast 4
    assertEquals 3, list.size()
    list.insertBefore(3, list.find(4))
    assertEquals 4, list.size()
    list.remove list.find(4)
    assertEquals 3, list.size()
    list.clear()
    assertEquals 0, list.size()
  }
}