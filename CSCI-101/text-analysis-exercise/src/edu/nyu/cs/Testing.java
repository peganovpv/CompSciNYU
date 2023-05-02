package edu.nyu.cs;

public class Testing {
    public static void main(String[] args) {
        // testing countOccurrences
        String text = "foo foo bar baz.baz.foo? baz bar,bum,foo foofoo!";
        String word = "foo";
        int count = countOccurrences(text, word);
        System.out.println(count);
    }

    // countOccurrences counts the number of times a word appears in a text.
    // It does not count consecutive occurrences without spaces of the word 
    // in the text.
    public static int countOccurrences(String text, String word) {
        int count = 0;
        int index = 0;
        while (index < text.length()) {
            int found = text.indexOf(word, index);
            if (found == -1) {
                break;
            }
            if (found == 0 || !Character.isLetter(text.charAt(found - 1))) {
                int end = found + word.length();
                if (end == text.length() || !Character.isLetter(text.charAt(end))) {
                    count++;
                }
            }
            index = found + 1;
        }
        return count;
    }
}