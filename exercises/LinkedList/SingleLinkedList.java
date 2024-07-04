package LinkedList;

public class SingleLinkedList {
    private static class Node {
        private int item;
        private Node next;
        Node (int item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node first;

    public void addLast(int item) {
        if (first == null) {
            first = new Node(item, null);
            return;
        }
        Node p = first;
        while (true) {
            if (p.next == null) {
                p.next = new Node(item, null);
                return;
            }
            p = p.next;
        }
    }

    public void addFirst(int item) {
        first = new Node(item, first);
    }

    public void insert(int item, int position) {
        if (position < 0) {
            return;
        }
        if (position == 0 || first == null) {
            first = new Node(item, first);
            return;
        }
        Node p = first;
        while (true) {
            if (position == 1 || p.next == null) {
                p.next = new Node(item, p.next);
                return;
            }
            p = p.next;
            position--;
        }
    }

    public void reverseIterative() {
        if (first == null) {
            return;
        }
        Node thisOne = first;
        Node nextOneReversed = null;
        while (true) {
            Node nextOne = thisOne.next;
            thisOne.next = nextOneReversed;
            if (nextOne == null) {
                break;
            }
            nextOneReversed = thisOne;
            thisOne = nextOne;
        }
        first = thisOne;
    }

    public void reverseRecursive() {
        first = reverseRecursive(first);
    }

    private static Node reverseRecursive(Node thisOne) {
        if (thisOne == null || thisOne.next == null) {
            return thisOne;
        }
        Node reversed = reverseRecursive(thisOne.next);
        thisOne.next.next = thisOne;
        thisOne.next = null;
        return reversed;
    }

    public static void main(String[] args) {
        SingleLinkedList a = new SingleLinkedList();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.insert(100, 0);
        a.insert(101, 1);
        a.insert(102, 2);
        a.insert(999, 999);
        SingleLinkedList b = new SingleLinkedList();
        b.insert(0, 0);
        a.reverseIterative();
        a.reverseIterative();
        b.reverseIterative();
        a.reverseRecursive();
    }
}
