package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Affirmation;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import util.BlackboardUtil;
import util.SentenceUtil;
import util.WordRegex;

public class SentenceStructureKnowledgeSource extends SentenceKnowledgeSource {

    private HashMap<WordType, List<String>> masterWordList = new HashMap<WordType, List<String>>();;
    private ArrayList<String> dict = new ArrayList<String>();

    public SentenceStructureKnowledgeSource() {
        masterWordList.put(WordType.ADJECTIVE, readFiles(new File("resources/words_adjectives.txt")));
        masterWordList.put(WordType.NOUN, readFiles(new File("resources/words_nouns.txt")));
        masterWordList.put(WordType.ARTICLE, readFiles(new File("resources/words_articles.txt")));
        masterWordList.put(WordType.PREPOSITION, readFiles(new File("resources/words_prepositions.txt")));
        masterWordList.put(WordType.VERB, readFiles(new File("resources/words_verbs.txt")));
        masterWordList.put(WordType.PRONOUN, readFiles(new File("resources/words_pronouns.txt")));
    }

    public List<String> readFiles(File file) {
        dict.clear();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) dict.add(s.next().toUpperCase());
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dict;
    }
    
    private enum WordType {
        ADJECTIVE, NOUN, ARTICLE, PREPOSITION, PRONOUN, VERB, ADVERB
    }

    public WordType getWordType(String w) {
        WordType finalType = null;
        for (WordType wt : masterWordList.keySet()) {
            List<String> dict = masterWordList.get(wt);
            if (dict.contains(w)) {
                finalType = wt;
                break;
            }
        }
        return finalType;
    }

    @Override
    public String toString() {
        return "SentenceStructureKnowledgeSource";
    }

    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);

        for (int w = 0; w < words.size() - 1; w++) {
			WordRegex wr1 = BlackboardUtil.getWordRegex(w);
			WordRegex wr2 = BlackboardUtil.getWordRegex(w + 1);
			
			if (wr1.unknownCount != 0 || wr2.unknownCount != 0) continue;
			
			//The rules implemented are:
			//1. Cannot have article + verb
	        //2. pronoun must be followed by verb
			if ((getWordType(wr1.regex) == WordType.ARTICLE && getWordType(wr2.regex) == WordType.VERB) || 
					(getWordType(wr1.regex) == WordType.PRONOUN && getWordType(wr2.regex) != WordType.VERB)) {
				
				//ask user which word(s) are wrong
		        System.out.println("Which word(s) are wrong?");
		        System.out.println("(0)" + wr1.regex + " and/or (1)" + wr2.regex);
		        String[] wrongLetters = BlackboardUtil.scanner.next().split(",");
	        	int[] wrongIndices = new int[wrongLetters.length];
	        	for (int i = 0; i < wrongLetters.length; i++) 
	        		wrongIndices[i] = Integer.parseInt(wrongLetters[i]);
	        	
	        	for (int index : wrongIndices) {
	        		//go through these cipherletters and mark them as not solved
	        		List<CipherLetter> ls = BlackboardUtil.getCurrentSentenceState().get(w + index);
	        		for (CipherLetter l : ls) l.setAffirmation(new Affirmation(l));
	        	}
		        		        
		        queue.add(new Assumption());
		        this.setPastAssumptions(queue);
			}
					
			
		}
        
    }
   

}