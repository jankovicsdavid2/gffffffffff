package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PalindromeChecker {

    private static final Logger logger = LogManager.getLogger(PalindromeChecker.class);

    public static void main(String[] args) {

        String word = "racecar"; // or dynamic input

        logger.info("Checking if " + word + " is a palindrome...");

        if (isPalindrome(word)) {
            logger.info("Result: " + word +" IS a palindrome!");
        } else {
            logger.info("Result: " + word + " is NOT a palindrome.");
        }
    }

    private static boolean isPalindrome(String word) {
        char[] charArrayOfWord = word.toLowerCase().toCharArray();

        for (int i = 0; i < charArrayOfWord.length / 2; i++) {
            if (charArrayOfWord[i] != charArrayOfWord[charArrayOfWord.length - i - 1]) {
                return false;
            }
        }

        return true;

    }
}
