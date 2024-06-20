public class ArrayDeque<T> {
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public void addFirst(T value) {
        T[] newItems;
        if (size == items.length) {
            newItems = (T[]) new Object[items.length * 2];
        } else {
            newItems = (T[]) new Object[items.length];
        }
        System.arraycopy(items, 0, newItems, 1, size);
        items = newItems;
        items[0] = value;
        size++;
    }

    public void addLast(T value) {
        if (size == items.length) {
            T[] newItems = (T[]) new Object[items.length * 2];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
        items[size] = value;
        size++;
    }

    public boolean isEmpty() { return size == 0;}

    public int size() { return size;}

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) return null;
        T value = items[0];
        T[] newItems;
        if ((double) (size - 1) / items.length <= 0.25) {
            newItems = (T[]) new Object[items.length / 2];
        } else {
            newItems = (T[]) new Object[items.length];
        }
        System.arraycopy(items, 1, newItems, 0, size - 1);
        items = newItems;
        size--;
        return value;
    }

    public T removeLast() {
        if (size == 0) return null;
        T value = items[size - 1];
        if ((double) (size - 1) / items.length <= 0.25) {
            T[] newItems = (T[]) new Object[items.length / 2];
            System.arraycopy(items, 0, newItems, 0, size - 1);
            items = newItems;
            size--;
            return value;
        }
        items[size - 1] = null;
        size--;
        return value;
    }

    public T get(int index) {
        if (index >= size || index < 0) return null;
        return items[index];
    }
}
