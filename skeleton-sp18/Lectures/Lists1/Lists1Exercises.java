package Lectures.Lists1;

public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        if (L.rest == null) {
            return new IntList(L.first + x, null);
        }
        return new IntList(L.first + x, incrList(L.rest, x));
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        IntList p = L;
        while (p != null) {
            p.first += x;
            p = p.rest;
        }
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println(L.size());
        System.out.println(L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
//         System.out.println(L.get(1));
//         System.out.println(incrList(L, 3));

        System.out.println(L.get(0));
        System.out.println(L.get(1));
        System.out.println(L.get(2));
        System.out.println();

        IntList K = incrList(L, 3);
        System.out.println(K.get(0));
        System.out.println(K.get(1));
        System.out.println(K.get(2));
        System.out.println();

        IntList P = dincrList(L, 3);
        System.out.println(P.get(0));
        System.out.println(P.get(1));
        System.out.println(P.get(2));
        System.out.println();

        // System.out.println(dincrList(L, 3));        
    }
}