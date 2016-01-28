package util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import domain.Sentence;
import domain.Word;

public final class SentenceUtil {

    public static Sentence updateAllOccurrences(Sentence sentence, String cipher, String plain) {
        String tmp = sentence.value();
        tmp.replace(cipher, plain);
        sentence.setSentence(tmp);
        return sentence;
    }
    /*
    public static List<Word> getWords(final Sentence sentence) {

        StringTokenizer toker = new StringTokenizer(sentence.value());
        ArrayList<Word> words = new ArrayList<Word>();

        while (toker.hasMoreTokens()) {
            String tmpWord = toker.nextToken();
            Word word = new Word(tmpWord);
            words.add(word);
        }

        return words;
    }
	*/
    /*
    public static List<Alphabet> getLetters(final Word word) {

        ArrayList<Alphabet> letters = new ArrayList<Alphabet>();

        int i = 0;

        for (i = 0; i < word.value().length(); i++) {
            Alphabet letter = new Alphabet(word.value().substring(i, i + 1));
            letters.add(letter);
        }

        return letters;
    }
    */

}
