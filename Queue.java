package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Md Golam Kibria
 */
public class Queue<T> implements Iterable<T> {

    Node<T> first;
    Node<T> last;
    int n;
    
    public static class Node<T> {
        T data;
        Node<T> next;
    }

    public Queue() {
        first = null;
        n=0;
    }
    
    public void enqueue(T data) {
        Node<T> oldLast = last;
        last = new Node<>();
        last.data = data;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        
        n++;
    }
    
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T data = first.data;
        first = first.next;
        n--;
        return data;
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return first.data;
    }
    
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
        
    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        q.enqueue("1");
        q.enqueue("2");
        q.enqueue("3");

        System.out.println(q.toString());

        for (String data : q) {
            System.out.print(data + " ");
        }
        System.out.println("");

        Iterator<String> it = q.iterator();
        while (it.hasNext()) {
            String data = it.next();
            System.out.print(data + " ");
        }
        
        while (!q.isEmpty()) {
            System.out.println(q.dequeue());
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new LinkedIterator(first);
    }
    
    private class LinkedIterator implements Iterator<T> {
        
        Node<T> current;

        public LinkedIterator(Node<T> current) {
            this.current = current;
        }
        

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
        
    }
}