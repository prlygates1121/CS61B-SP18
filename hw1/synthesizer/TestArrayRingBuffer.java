package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        // write tests
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        assertTrue(arb.isEmpty());
        arb.enqueue(1);
        assertFalse(arb.isEmpty());
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        assertTrue(arb.isFull());
        assertEquals(1, (int) arb.dequeue());
        assertEquals(2, (int) arb.dequeue());
        assertEquals(3, (int) arb.dequeue());
        assertEquals(4, (int) arb.dequeue());
        assertEquals(5, (int) arb.dequeue());
        assertEquals(6, (int) arb.dequeue());
        assertEquals(7, (int) arb.dequeue());
        assertEquals(8, (int) arb.dequeue());
        assertEquals(9, (int) arb.dequeue());
        assertEquals(10, (int) arb.dequeue());
        assertTrue(arb.isEmpty());
        // test iterator
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);
        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);
        int j = 1;
        for (int i : arb) {
            assertEquals(j, i);
            j++;
        }
    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
