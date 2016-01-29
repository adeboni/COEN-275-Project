package util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import domain.Alphabet;
import domain.Sentence;
import domain.Word;


public final class SentenceUtil {

    /**
     * Public method to replace all occurrences of a cipher text letter with a
     * plain text equivalent.  This method does not affect blackboard objects. Only
     * the Sentence value (String) is changed.
     * 
     * @param sentence
     *      the Sentence blackboard object
     * @param cipher
     *      the cipher letter String to change
     * @param plain
     *      the plain text String replacement
     * @return updated sentence String
     */
    public static Sentence updateAllOccurrences(Sentence sentence, String cipher, String plain) {
        sentence.setSentence(sentence.value().replace(cipher, plain));
        return sentence;
    }

    /**
     * 
     * @param sentence
     *      the Sentence object parent
     * @return
     *      a List of word blackboard objects
     */
    public static List<Word> getWords(final Sentence sentence) {

        StringTokenizer toker = new StringTokenizer(sentence.value());
        ArrayList<Word> words = new ArrayList<Word>();

        while (toker.hasMoreTokens()) {
            words.add(new Word(toker.nextToken()));
        }

        return words;
    }

    /**
     * Public method to return children cipher letters from word blackboard objects
     * 
     * @param word
     *      the Word object parent
     * @return
     *      a List of cipher letter blackboard objects
     */
    public static List<Alphabet> getLetters(final Word word) {

        ArrayList<Alphabet> letters = new ArrayList<Alphabet>();

        for (int i = 0; i < word.value().length(); i++) {
            letters.add(new Alphabet(word.value().substring(i, i + 1)));
        }

        return letters;
    }


}
