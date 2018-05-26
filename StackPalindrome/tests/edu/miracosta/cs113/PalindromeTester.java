package edu.miracosta.cs113;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PalindromeTester {

    /**
     * Checks if parameter is a palindrome using stacks and
     * ignores whitespace + case sensitivity; doesn't ignore digits/symbols
     *
     * @param s any chars allowed,
     * @return returns true if a palindrome (ignoring whitespace and case sensitivity), false otherwise
     */
    private boolean isPalindrome(String s) {
        //Checking for null at the very beginning
        if(s == null)
        {
            throw new IllegalArgumentException();
        }

        ArrayListStack original = new ArrayListStack();
        ArrayListStack check = new ArrayListStack();
        String inputString = s.toLowerCase().replaceAll(" ", "");

        //Marks the halfway point of the input string
        int half = inputString.length() / 2;
        
        if(inputString.length() == 1 || inputString.equals("")) {
            return true;
        }
        else {
            for(int i = 0; i < inputString.length(); i++) {
                original.push(inputString.charAt(i));
            }

            for(int i = 0; i < half; i++) {
                check.push(original.pop());
            }

            if(inputString.length() % 2 != 0) {
                original.pop();
            }

            for(int i = 0; i < half; i++) {
                if(check.peek() == original.peek()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void testErrors() {
        try {
            isPalindrome(null);
            fail("Checking null to see if it's a palindrome should throw IllegalArgumentException!");
        } catch (IllegalArgumentException iae) { /* Test Passed! */ }
    }

    @Test
    public void testSimpleTrueCases() {
        assertTrue("This test is a palindrome", isPalindrome(""));
        assertTrue("This test is a palindrome", isPalindrome(" "));
        assertTrue("This test is a palindrome", isPalindrome("A"));
        assertTrue("This test is a palindrome", isPalindrome("7"));
        assertTrue("This test is a palindrome", isPalindrome("%"));

        assertTrue("This test is a palindrome", isPalindrome("  "));
        assertTrue("This test is a palindrome", isPalindrome("BB"));
        assertTrue("This test is a palindrome", isPalindrome("33"));
        assertTrue("This test is a palindrome", isPalindrome("**"));
    }

    @Test
    public void testSimpleFalseCases() {
        assertFalse("This test is NOT a palindrome", isPalindrome("AC"));
        assertFalse("This test is NOT a palindrome", isPalindrome("71"));
        assertFalse("This test is NOT a palindrome", isPalindrome("@+"));
    }

    @Test
    public void testWhitespaceCases() {
        assertTrue("This test is a palindrome", isPalindrome(" x "));
        assertTrue("This test is a palindrome", isPalindrome(" t   t  "));
        assertTrue("This test is a palindrome", isPalindrome(" 5 5"));
        assertTrue("This test is a palindrome", isPalindrome(" #      # "));

        assertFalse("This test is NOT a palindrome", isPalindrome("m   n  "));
        assertFalse("This test is NOT a palindrome", isPalindrome("   8  7 "));
        assertFalse("This test is NOT a palindrome", isPalindrome("  ^      &  "));
    }

    @Test
    public void testCaseSensitivityCases() {
        assertTrue("This test is a palindrome", isPalindrome("ABba"));
        assertTrue("This test is a palindrome", isPalindrome("roTOR"));
        assertTrue("This test is a palindrome", isPalindrome("rAceCaR"));
    }

    @Test
    public void testComplexCases() {
        assertTrue("This test is a palindrome", isPalindrome("fOO race CAR ooF"));
        assertTrue("This test is a palindrome", isPalindrome("AbBa ZaBba"));
        assertTrue("This test is a palindrome", isPalindrome("1 3 3 7  331"));
        assertTrue("This test is a palindrome", isPalindrome("sT RJKLEeE R#@ $A$ @# REeEL K  JRT s"));

    }

}
