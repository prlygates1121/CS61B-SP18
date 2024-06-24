package Lectures.Testing;

public class Sort {
    public static void sort(String[] input) {
        for (int i = 0 ; i < input.length - 1; i++) {
            swap(input, i, findSmallest(input, i));
        }
    }

    private static int findSmallest(String[] input, int start) {
        int smallest = start;
        for (int i = start + 1; i < input.length; i++) {
            if (input[i].compareTo(input[smallest]) < 0) {
                smallest = i;
            }
        }
        return smallest;
    }

    private static void swap(String[] input, int a, int b) {
        String temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
