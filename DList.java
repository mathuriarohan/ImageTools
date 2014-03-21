/* DList.java */

/**
 *  A DList is a mutable doubly-linked list.  (No sentinel, not
 *  circularly linked.)
 */

public class DList {

  public DListNode head;
  public DListNode tail;
  public long size;

  public DList() {
    head = null;
    tail = null;
    size = 0;
  }

  /**
   *  DList() constructor for a one-node DList.
   */
  public DList(Run a) {
    head = new DListNode();
    tail = head;
    head.item = a;
    size = 1;
  }

  /**
   *  DList() constructor for a two-node DList.
   */
  public DList(Run a, Run b) {
    head = new DListNode();
    head.item = a;
    tail = new DListNode();
    tail.item = b;
    head.next = tail;
    tail.prev = head;
    size = 2;
  }

  /**
   *  insertFront() inserts an item at the front of a DList.
   */
  public void insertFront(Run i) {
    if (head != null){
      DListNode temp = new DListNode(i);
      temp.next = head;
      head.prev = temp;
      head = temp;
    }
    else {
      head = new DListNode(i);
      tail = head;
    }
    size++;
  }

  /**
   *  removeFront() removes the first item (and node) from a DList.  If the
   *  list is empty, do nothing.
   */
  public void removeFront() {
    // Your solution here.
    if (head != null){
      size--;
      if (head.next != null){
        DListNode temp = head;
        head = head.next;
        head.prev = null;
        temp.next = null;
      }
      else{
        head = null;
        tail = null;
      }
    }
  }

  public void insertEnd(Run i) {
    if (tail == null){
      tail = new DListNode(i);
      head = tail;
    }
    else {
      tail.next = new DListNode(i);
      tail.next.prev = tail;
      tail = tail.next;
    }
    size++;
  }

  /**
   *  toString() returns a String representation of this DList.
   *
   */
  public String toString() {
    String result = "[  ";
    DListNode current = head;
    while (current != null) {
      result = result + current.item + "  ";
      current = current.next;
    }
    return result + "]";
  }

}
