package Lectures.Lists1;


import java.time.format.DateTimeFormatter;

public class IntList {
    public int first;
    public IntList rest;



    IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    public int size() {
        if (rest == null) {
            return 1;
        } else {
            return (rest.size() + 1);
        }
    }

    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize++;
            p = p.rest;
        }
        return totalSize;
    }

    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    public int iterativeGet(int i) {
        IntList p = this;
        for (int j = 0; j < i; j++) {
            p = p.rest;
        }
        return p.first;
    }

    public static void main(String[] args) {
        IntList L = new IntList(35, null);
        L = new IntList(25, L);
        L = new IntList(15, L);
        L = new IntList(5, L);
        System.out.println(L.get(-222));
        System.out.println(L.iterativeGet(-222));

    }
}
