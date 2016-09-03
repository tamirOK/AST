import java.util.NoSuchElementException;

/**
 * Created by nezumi on 8/30/16.
 */
public class LinkedStack<T> extends MySinglyLinkedList<T> {
    public T pop() {
        if (size == 0)
            throw new NoSuchElementException();
        T result = head.val;
        head = head.next;
        size--;
        return result;
    }

    public T top() {
        if (size == 0)
            throw new NoSuchElementException();
        return head.val;
    }

    public void push(T item) {
        size++;
        Node temp = new Node(item);
        if (head == null)
            head = temp;
        else {
            temp.next = head;
            head = temp;
        }
    }

}

