package Lectures.Lists2;

public class SLList {

    private static class IntNode {
        public int item;
        public IntNode next;
        IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    SLList() {
        sentinel = new IntNode(-1, null);
        size = 0;
    }

    SLList(int i) {
        sentinel = new IntNode(-1, null);
        sentinel.next = new IntNode(i, null);
        size = 1;
    }

    public int getFirst() {
        return sentinel.next.item;
    }

    public void addFirst(int i) {
        sentinel.next = new IntNode(i, sentinel.next);
        size++;
    }

    public void addLast_ite(int i) {
        IntNode p = sentinel;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new IntNode(i, null);
        size++;
    }

//    public int size_ite() {
//        IntNode p = first;
//        int totalSize = 0;
//        while (p != null) {
//            totalSize++;
//            p = p.next;
//        }
//        return totalSize;
//    }
//
//    private static int size(IntNode n) {
//        if (n.next == null) {
//            return 1;
//        }
//        return size(n.next) + 1;
//    }
//
//    public int size() {
//        return size(first);
//    }

    public int size() {
        return size;
    }


}

class test {
    public static void main(String[] args) {
        SLList list1 = new SLList(2);
//        list1.addFirst(1);
//        list1.addFirst(8);
//        list1.addLast_ite(2);
        System.out.println(list1.size());
    }
}
