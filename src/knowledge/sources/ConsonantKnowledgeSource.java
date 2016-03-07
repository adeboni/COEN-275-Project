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

public class ConsonantKnowledgeSource extends LetterKnowledgeSource {

    @Override
    public String toString() {
        return "ConsonantKnowledgeSource";
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
                if (!isConsonant(letters.get(i).value().charAt(0)) && 
                		!isConsonant(letters.get(i + 1).value().charAt(0)) && 
                		wr.regex.charAt(i + 2) == '.') {
                    index = i + 2;
                    break;
                }
            }
			
			if (index < 0 || addedLetters.contains(letters.get(index).value())) continue;
									
			for (Character c : consonants) {
				if (blackboard.checkPair(letters.get(index).value(), c) || blackboard.boardedPlainLetters.contains(c.toString())) 
					continue;
				
				System.out.println("Consonant KS setting " + letters.get(index).value() + " to " + c);
				
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
    
    public ConsonantKnowledgeSource() {
    	char consonantArray[] = {'T', 'N', 'S', 'R', 'H', 'D', 'L', 'C', 'M', 'F', 'Y', 'W', 'G', 'P', 'B', 'V', 'K', 'X', 'Q', 'J', 'Z'};
		for (char c : consonantArray) consonants.add(c);
	}
	
	private boolean isConsonant(char c) {
		return consonants.contains(c);
	}
	
	private List<Character> consonants = new ArrayList<Character>();
}
