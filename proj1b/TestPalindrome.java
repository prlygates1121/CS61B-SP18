import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

//    @Test
//    public void testIsPalindrome() {
//        assertTrue(palindrome.isPalindrome("hannah"));
//        assertTrue(palindrome.isPalindrome("level"));
//        assertTrue(palindrome.isPalindrome("a"));
//        assertTrue(palindrome.isPalindrome(""));
//        assertTrue(palindrome.isPalindrome("AaA"));
//        assertTrue(palindrome.isPalindrome("23332"));
//
//        assertFalse(palindrome.isPalindrome("horse"));
//        assertFalse(palindrome.isPalindrome("cat"));
//        assertFalse(palindrome.isPalindrome("contemplate"));
//        assertFalse(palindrome.isPalindrome("good"));
//        assertFalse(palindrome.isPalindrome("$A"));
//        assertFalse(palindrome.isPalindrome("1A"));
//        assertFalse(palindrome.isPalindrome("aAaa"));
//        assertFalse(palindrome.isPalindrome("23333"));
//    }
//
//    @Test
//    public void testIsPalindromeOffByOne() {
//        OffByOne a = new OffByOne();
//        assertTrue(palindrome.isPalindrome("hanmbi", a));
//        assertTrue(palindrome.isPalindrome("", a));
//        assertTrue(palindrome.isPalindrome("flake", a));
//        assertFalse(palindrome.isPalindrome("433980213", a));
//        assertFalse(palindrome.isPalindrome("dd", null));
//
//    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("aba"));
        assertTrue(palindrome.isPalindrome("aaccbbbccaa"));

        assertFalse(palindrome.isPalindrome("ab"));
        assertFalse(palindrome.isPalindrome("ababba"));
    }

    @Test
    public void testIsOffByOnePalindrome() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("aba", cc));
    }
}
