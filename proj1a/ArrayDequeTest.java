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
        what.addLast(0);
        what.addLast(1);
        what.addLast(2);
        what.addLast(3);
        what.addLast(4);
        what.addLast(5);
        what.addLast(6);
        what.addLast(7);
        // remove and print removed value
        boolean passed = (what.removeFirst() == 0);
        passed = (what.removeLast() == 1) && passed;
        passed = (what.removeFirst() == 2) && passed;
        passed = (what.removeLast() == 3) && passed;
        passed = (what.removeFirst() == 4) && passed;
        passed = (what.removeLast() == 5) && passed;
        passed = (what.removeFirst() == 6) && passed;
        passed = (what.removeLast() == 7) && passed;

        // check if empty
        passed = checkEmpty(true, what.isEmpty()) && passed;
        // check size
        passed = checkSize(0, what.size()) && passed;
        // add from back and front
        what.addFirst(0);
        what.addLast(1);
        what.addFirst(2);
        what.addLast(3);
        what.addFirst(4);
        what.addLast(5);
        what.addFirst(6);
        what.addLast(7);
        what.addFirst(8);
        what.addLast(9);
        // check size
        passed = checkSize(10, what.size()) && passed;
        // check get
        passed = (what.get(0) == 8) && passed;
        passed = (what.get(1) == 6) && passed;
        passed = (what.get(2) == 4) && passed;
        passed = (what.get(3) == 2) && passed;
        passed = (what.get(4) == 0) && passed;
        passed = (what.get(5) == 1) && passed;
        passed = (what.get(6) == 3) && passed;
        passed = (what.get(7) == 5) && passed;
        passed = (what.get(8) == 7) && passed;
        passed = (what.get(9) == 9) && passed;
        // check if empty
        passed = checkEmpty(false, what.isEmpty()) && passed;
        // remove from back and front
        passed = (what.removeFirst() == 8) && passed;
        passed = (what.removeLast() == 9) && passed;
        passed = (what.removeFirst() == 6) && passed;
        passed = (what.removeLast() == 7) && passed;
        passed = (what.removeFirst() == 4) && passed;
        passed = (what.removeLast() == 5) && passed;
        passed = (what.removeFirst() == 2) && passed;
        passed = (what.removeLast() == 3) && passed;
        passed = (what.removeFirst() == 0) && passed;
        passed = (what.removeLast() == 1) && passed;
        // check if empty
        passed = checkEmpty(true, what.isEmpty()) && passed;
        // check size
        passed = checkSize(0, what.size()) && passed;
        // finish test
        printTestStatus(passed);
    }

    public static void nullTest() {
        System.out.println("Running nullTest.");

        ArrayDeque<String> a = new ArrayDeque<>();
        boolean passed = (a.get(0) == null);
        a.addLast("holy");
        passed = (a.get(0).equals("holy")) && passed;
        printTestStatus(passed);
    }

    // excessive removes test
    public static void excessiveRemoves() {
        System.out.println("Running excessiveRemoves.");

        ArrayDeque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 100000; i++) {
            a.addLast(i);
        }
        for (int i = 0; i < 1000000; i++) {
            a.removeFirst();
        }
        boolean passed = checkEmpty(true, a.isEmpty());
        passed = checkSize(0, a.size()) && passed;
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
