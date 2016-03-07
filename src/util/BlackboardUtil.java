package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import blackboard.Blackboard;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import knowledge.sources.SolvedKnowledgeSource;


public final class BlackboardUtil {

    /**
     * Public method to output the sentence and underline any corrected cipher
     * letters. Also any letters that are underlined are plaintext letters
     * (Alphabets) and also Affirmations exist in blackboard problem domain.
     */
    public static void outputProgress(Blackboard blackboard) {

        Sentence sentence = blackboard.getSentence();

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
        currentSentenceState.clear();

        for (Word word : words) {

            List<CipherLetter> list = word.getLetters();
            currentSentenceState.add(list);

            for (CipherLetter letter : list) {
                if (letter.getAffirmations().getSolvedLetter().getPlainLetter() != null) {
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
    
    public static void initSentenceState(Sentence sentence) {
        currentSentenceState.clear();
        for (Word word : sentence.getWords()) 
            currentSentenceState.add(word.getLetters());
    }
    
    private static List<List<CipherLetter>> currentSentenceState = new ArrayList<List<CipherLetter>>();
    
    public static List<List<CipherLetter>> getCurrentSentenceState() {
    	return currentSentenceState;
    }
    
    public static Scanner scanner = new Scanner(System.in);
    
    
    
    public static WordRegex getWordRegex(int word) {
    	String regex = "";
		int unknownCount = 0;
		for (CipherLetter cl : BlackboardUtil.getCurrentSentenceState().get(word)) {
			if (cl.getAffirmations().getSolvedLetter().getPlainLetter() == null) {
				regex += ".";
				unknownCount++;
			}
			else {
				regex += cl.getAffirmations().getSolvedLetter().getPlainLetter();
			}
		}
		
		if (unknownCount == 0 && !SolvedKnowledgeSource.isRealWord(regex)) {
			unknownCount = regex.length();
			regex = "";
			for (int i = 0; i < unknownCount; i++) regex += ".";
		}
		
		return new WordRegex(unknownCount, regex);
    }
    
    
}
