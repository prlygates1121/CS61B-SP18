package Lectures.Testing;

public class TestSort {
    public static void main(String[] args) {
        String[] test = {"B", "D", "A", "E", "C"};
        String[] expected = {"A", "B", "C", "D", "E"};

        Sort.sort(test);
        for (String s : test) {
            System.out.print(s + " ");
        }
        org.junit.Assert.assertArrayEquals(expected, test);
    }
}
