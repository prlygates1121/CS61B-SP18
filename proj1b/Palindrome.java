public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        while (true) {
            Character left = deque.removeFirst();
            Character right = deque.removeLast();
            if (left == null || right == null) {
                return true;
            }
            if (!left.equals(right)) {
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.isEmpty()) {
            return true;
        }
        if (cc == null) {
            return false;
        }
        Deque<Character> deque = wordToDeque(word);
        while (true) {
            Character left = deque.removeFirst();
            Character right = deque.removeLast();
            if (left == null || right == null) {
                return true;
            }
            if (!cc.equalChars(left, right)) {
                return false;
            }
        }
    }
}
