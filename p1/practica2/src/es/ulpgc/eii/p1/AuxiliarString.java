package es.ulpgc.eii.p1;

/**
 * @author David Bohmann
 */
public class AuxiliarString {

    /**
     * Method takes one input String and splits it into words
     *
     * @param text Text to split in words
     * @return Array of words
     */
    public static String[] palabras(String text) {
        String[] words = new String[(text.length() / 2) + 1]; //array of words, max length possible
        int wordsIter = 0; //pointer to position in words array

        String word = ""; //init word
        for (int i = 0; i < text.length(); i++) { //go through text by characters
            char letter = text.charAt(i);
            if (Character.isLetterOrDigit(letter)) { //check if character is letter of digit
                word = word + letter; //if yes, add the character to word
                if (i == text.length() - 1) { //word is last in the input string
                    words[wordsIter] = word; //save word to array
                    wordsIter++; //go to next item in array
                    word = ""; //init word again
                }
            } else {
                if (word.length() > 0) { //check for whitespace after comma, dot, etc.
                    words[wordsIter] = word; //save word to array
                    wordsIter++; //go to next item in array
                    word = ""; //init word again
                }
            }
        }

        String[] finalWords = new String[wordsIter]; //init new array with actual size equal to number of words
        for (int i = 0; i < wordsIter; i++) {
            finalWords[i] = words[i]; // copy from temp array
        }

        return finalWords;
    }

    /**
     * Method takes one input String and splits it into words
     * if the word is already in array, it is not put in there again
     *
     * @param text Text to split in words
     * @return Array of words that are not repeated
     */
    public static String[] palabrasDistintas(String text) {

        String[] words = new String[(text.length() / 2) + 1]; //array of words, max length possible
        int wordsIter = 0; //pointer to position in words array

        String word = ""; //init word
        outerloop:
        for (int i = 0; i < text.length(); i++) { //go through text by characters
            char letter = text.charAt(i);
            if (Character.isLetterOrDigit(letter)) { //check if character is letter of digit
                word = word + letter; //if yes, add the character to word
                if (i == text.length() - 1) { //word is last in the input string
                    for (String savedWord : words) { //check if word is already in the array
                        if (word.equals(savedWord)) {
                            word = ""; //init word again, dont save to array
                            continue outerloop; //continue with next letter
                        }
                    }
                    words[wordsIter] = word; //save word to array
                    wordsIter++; //go to next item in array
                    word = ""; //init word again
                }
            } else {
                if (word.length() > 0) { //check for whitespace after comma, dot, etc.
                    for (String savedWord : words) { //check if word is already in the array
                        if (word.equals(savedWord)) {
                            word = ""; //init word again, dont save to array
                            continue outerloop; //continue with next letter
                        }
                    }
                    words[wordsIter] = word; //save word to array
                    wordsIter++; //go to next item in array
                    word = ""; //init word again
                }
            }
        }

        String[] finalWords = new String[wordsIter]; //init new array with actual size equal to number of words
        for (int i = 0; i < wordsIter; i++) {
            finalWords[i] = words[i]; // copy from temp array
        }

        return finalWords;
    }
}
