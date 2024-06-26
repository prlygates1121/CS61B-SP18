@SuppressWarnings("unchecked")
public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void rightLast() {
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast++;
        }
    }

    private void leftLast() {
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast--;
        }
    }

    private void leftFirst() {
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst--;
        }
    }

    private void rightFirst() {
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
    }

    private void expand() {
        T[] bigItems = (T[]) new Object[items.length * 2];
        int i = 0;
        rightFirst();
        do {
            bigItems[i] = items[nextFirst];
            i++;
            rightFirst();
        } while (nextFirst != nextLast);
        items = bigItems;
        nextFirst = items.length - 1;
        nextLast = items.length / 2;
    }

    private void shrink() {
        T[] smallItems = (T[]) new Object[items.length / 2];
        int i = 0;
        rightFirst();
        do {
            smallItems[i] = items[nextFirst];
            i++;
            rightFirst();
        } while (nextFirst != nextLast);
        items = smallItems;
        nextFirst = items.length - 1;
        nextLast = i;
    }

    @Override
    public void addFirst(T value) {
        if (size == items.length) {
            expand();
        }
        items[nextFirst] = value;
        leftFirst();
        size++;
    }

    @Override
    public void addLast(T value) {
        if (size == items.length) {
            expand();
        }
        items[nextLast] = value;
        rightLast();
        size++;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (double) size / items.length < 0.25) {
            shrink();
        }
        rightFirst();
        T value = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return value;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (double) size / items.length < 0.25) {
            shrink();
        }
        leftLast();
        T value = items[nextLast];
        items[nextLast] = null;
        size--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }


//    public void addFirst(T value) {
//        if (size >= items.length) {
//            expand();
//        }
//        items[nextFirst] = value;
//        nextFirst--;
//        size++;
//    }
//    public void addLast(T value) {
//        if (size >= items.length) {
//            expand();
//        }
//        items[nextLast] = value;
//        nextLast++;
//        size++;
//    }

    //    private void expand() {
//        int head = nextLast;
//        int tail = items.length - head;
//        T[] newItems = (T[]) new Object[items.length * 2];
//        System.arraycopy(items, 0, newItems, 0, head);
//        System.arraycopy(items, nextLast, newItems, newItems.length - tail, tail);
//        items = newItems;
//        nextFirst = newItems.length - tail - 1;
//    }

}
