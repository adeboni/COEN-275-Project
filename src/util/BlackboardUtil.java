package util;

import java.util.List;

import blackboard.Blackboard;
import domain.Alphabet;
import domain.BlackboardObject;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;


public final class BlackboardUtil {

    /**
     * Public method to output the sentence and underline any corrected cipher
     * letters. Also any letters that are underlined are plaintext letters
     * (Alphabets) and also Affirmations exist in blackboard problem domain.
     */
    public static void outputProgress(Blackboard blackboard) {

        Sentence sentence = null;
        
        for (BlackboardObject obj : blackboard) {
            if (obj.getClass().equals(domain.Sentence.class)) {
                sentence = (Sentence) obj;
            }
        }

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("-- THE BLACKBOARD");
        System.out.println("-----------------------------------------------------------------------------");
        
        System.out.println("ORIGINAL: " + sentence.value());

        String markers = getAffirmationMarkings(sentence);

        /**
         * Use this to show that no markers exist
         */
        if (markers.equals("")) {
            markers = "___________________________";
        }

        System.out.println("PROGRESS: " + markers);
        System.out.println("-----------------------------------------------------------------------------");

    }
    

    /**
     * Private method to get affirmation markings for console output line below
     * working sentence
     * 
     * @param sentence
     * @return a special string of underscores
     */
    private static String getAffirmationMarkings(Sentence sentence) {

        String markerLine = "";
        int wordcount = 0;
        int loopcount = 0;

        List<Word> words = sentence.getWords();
        wordcount = words.size();

        for (Word word : words) {

            List<CipherLetter> list = word.getLetters();

            for (CipherLetter letter : list) {
                if (letter.getAffirmations().getSolvedLetter() != null) {
                    // affirmation and we have an assertion
                    markerLine = markerLine.concat(letter.getAffirmations().getSolvedLetter().getPlainLetter()); // was underscore
                } else {
                    markerLine = markerLine.concat(letter.value()); // was space
                }
            }
            if (loopcount < wordcount) {
                markerLine = markerLine.concat(" ");
            }
            loopcount++;

        }
        return markerLine;
    }

    
}
