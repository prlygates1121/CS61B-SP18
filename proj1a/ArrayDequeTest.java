/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct, 
     * finally printing the results.
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterward. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);

    }

    public static void extraTest() {
        System.out.println("Running extra test.");

        ArrayDeque<Integer> what = new ArrayDeque<>();
        what.addFirst(1);
        what.addFirst(2);
        what.addFirst(3);
        what.addFirst(4);
        what.removeFirst();
        what.removeFirst();
        what.removeFirst();
        what.removeFirst();
        what.addFirst(5);
        what.addFirst(6);
        what.addFirst(7);
        what.addFirst(6);
        what.addFirst(7);
        what.addFirst(5);
        what.addFirst(6);
        what.addFirst(6);
        what.addFirst(7);
        what.addFirst(5);
        System.out.println(what.get(0));
        System.out.println(what.get(1));
        System.out.println(what.get(2));
        System.out.println(what.get(3));
        System.out.println(what.get(4));
        System.out.println(what.get(5));
        System.out.println(what.get(6));
        System.out.println(what.get(7));
        System.out.println(what.get(8));
        System.out.println(what.get(9));
        System.out.println(what.get(10));
        System.out.println(what.get(11));
        what.addFirst(6);
        what.addFirst(5);
        what.addFirst(6);
        what.addFirst(7);

        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        System.out.println(what.removeFirst());
        what.addLast(0);
        what.addLast(1);
        what.addLast(2);
        what.addLast(3);
        what.addLast(4);
        what.addLast(5);
        what.addLast(0);
        what.addLast(1);
        what.addLast(2);
        what.addLast(3);
        what.addLast(4);
        what.addLast(5);
        System.out.println();
    }

    public static void nullTest() {
        System.out.println("Running nullTest.");

        ArrayDeque<String> a = new ArrayDeque<>();
        boolean passed = (a.get(0) == null);
        a.addLast("holy");
        passed = (a.get(0).equals("holy")) && passed;
        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        extraTest();
        nullTest();
    }
} 