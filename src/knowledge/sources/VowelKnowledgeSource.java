package knowledge.sources;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import util.BlackboardUtil;
import util.SentenceUtil;
import util.WordRegex;

public class VowelKnowledgeSource extends LetterKnowledgeSource {

    @Override
    public String toString() {
        return "VowelKnowledgeSource";
    }

    
    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);
        HashSet<String> addedLetters = new HashSet<String>();

        for (int w = 0; w < words.size(); w++) {
			List<CipherLetter> letters = SentenceUtil.getLetters(words.get(w));
			WordRegex wr = BlackboardUtil.getWordRegex(w);
			
			if (wr.unknownCount == 0) continue;
			
			int index = -1;
            for (int i = 0; i < letters.size() - 2; i++) {
                if (!isVowel(letters.get(i).value().charAt(0)) && 
                		!isVowel(letters.get(i + 1).value().charAt(0)) && 
                		wr.regex.charAt(i + 2) == '.') {
                    index = i + 2;
                    break;
                }
            }
			
			if (index < 0 || addedLetters.contains(letters.get(index).value())) continue;
									
			for (Character c : vowels) {
				if (blackboard.checkPair(letters.get(index).value(), c) || blackboard.boardedPlainLetters.contains(c.toString())) 
					continue;
				
				System.out.println("Vowel KS setting " + letters.get(index).value() + " to " + c);
				
				Assumption assumption = new Assumption();
				assumption.setCipherLetter(letters.get(index).value());
				assumption.setPlainLetter(c.toString());
				queue.add(assumption);
				
				addedLetters.add(letters.get(index).value());
				blackboard.boardedPlainLetters.add(c.toString());
				break;
			}
		}
        
		this.setPastAssumptions(queue);
	}
    
    public VowelKnowledgeSource() {
		char vowelArray[] = {'E', 'A', 'O', 'I', 'U'};
		for (char c : vowelArray) vowels.add(c);
	}
	
    private boolean isVowel(char c) {
		return vowels.contains(c);
	}
	
	private List<Character> vowels = new ArrayList<Character>();

}
