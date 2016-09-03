/**
 * Created by nezumi on 8/30/16.
 */
public class LinkedQueue<T> extends MySinglyLinkedList<T> {
    Node tail = null;

    public void enqueue(T item) {
        size++;
        Node temp = new Node(item);
        if (tail == null)
            tail = head = temp;
        else {
            tail.next = temp;
            tail = temp;
        }
    }

    public T dequeue() {
        size--;
        T result = head.val;
        head = head.next;
        if (head == null)
            tail = null;
        return  result;
    }

}
