package LinkedList;

public class SingleLinkedList_BARE {
    public int item;
    public SingleLinkedList_BARE rest;

    public SingleLinkedList_BARE() {
        item = 0;
        rest = null;
    }

    public SingleLinkedList_BARE(int item, SingleLinkedList_BARE rest) {
        this.item = item;
        this.rest = rest;
    }

    public static SingleLinkedList_BARE squareIterative(SingleLinkedList_BARE original) {
        if (original == null) {
            return null;
        }
        SingleLinkedList_BARE pOriginal = original;
        SingleLinkedList_BARE pNew = new SingleLinkedList_BARE(original.item * original.item, null);
        SingleLinkedList_BARE result = pNew;
        while (true) {
            pOriginal = pOriginal.rest;
            if (pOriginal == null) {
                break;
            }
            pNew.rest = new SingleLinkedList_BARE(pOriginal.item * pOriginal.item, null);
            pNew = pNew.rest;
        }
        return result;
    }

    public static SingleLinkedList_BARE squareRecursive(SingleLinkedList_BARE original) {
        if (original == null) {
            return null;
        }
        return new SingleLinkedList_BARE(original.item * original.item, squareRecursive(original.rest));
    }

    public void dSquareIterative() {
        SingleLinkedList_BARE p = this;
        while (true) {
            p.item *= p.item;
            if (p.rest == null) {
                return;
            }
            p = p.rest;
        }
    }

    public void dSquareRecursive() {
        item *= item;
        if (rest == null) {
            return;
        }
        rest.dSquareRecursive();
    }

    public static void main(String[] args) {
        SingleLinkedList_BARE a = new SingleLinkedList_BARE(1, new SingleLinkedList_BARE(2, new SingleLinkedList_BARE(3, new SingleLinkedList_BARE(4, new SingleLinkedList_BARE(5, null)))));
        SingleLinkedList_BARE b = squareIterative(a);
        SingleLinkedList_BARE c = squareRecursive(a);
        a.dSquareIterative();
        a.dSquareRecursive();
    }
}
