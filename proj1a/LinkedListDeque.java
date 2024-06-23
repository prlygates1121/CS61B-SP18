public class LinkedListDeque<T> {
    private static class Node<T> {
        Node<T> prev;
        T item;
        Node<T> next;
        Node(Node<T> p, T i, Node<T> n) {
            prev = p;
            next = n;
            item = i;
        }
    }

    private final Node<T> sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T i) {
        sentinel.next.prev = new Node<>(sentinel, i, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size++;
    }

    public void addLast(T i) {
        sentinel.prev.next = new Node<>(sentinel.prev, i, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;
    }

    public void printDeque() {
        Node<T> p = sentinel;
        for (int i = 0; i < size; i++) {
            p = p.next;
            System.out.print(p.item + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) return null;
        T value = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return value;
    }

    public T removeLast() {
        if (size == 0) return null;
        T value = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return value;
    }

    public T get(int index) {
        if (index >= size || index < 0) return null;
        Node<T> p = sentinel.next;
        for (int i = 0; i < index; i++) p = p.next;
        return p.item;
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0) return null;
        return get(sentinel.next, index);
    }

    private T get(Node<T> n, int index) {
        if (index == 0) return n.item;
        return get(n.next, index - 1);
    }

    public int size() { return size;}

    public boolean isEmpty() { return size == 0;}

}
