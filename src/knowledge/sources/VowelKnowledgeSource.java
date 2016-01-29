package knowledge.sources;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assumption;
import domain.Sentence;
import domain.Word;
import util.SentenceUtil;

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
        
        
    }
    
    public VowelKnowledgeSource() {
		char vowelarray[] = {'a', 'e', 'i', 'o', 'u'};
		for (int i = 0; i < vowelarray.length; ++i) {
		  vowels.add(new Character(vowelarray[i]));
		  vowels.add(Character.toUpperCase(vowelarray[i]));
		}
	}
	
	boolean isVowel(char c) {
		return vowels.contains(new Character(c));
	}
	
	Set<Character> vowels = new HashSet<Character>();

}
