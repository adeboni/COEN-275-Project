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
        
        
    }
    
    public ConsonantKnowledgeSource() {
		char consonantarray[] = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p',
			'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z',};
		for (int i = 0; i < consonantarray.length; ++i) {
		  consonants.add(new Character(consonantarray[i]));
		  consonants.add(Character.toUpperCase(consonantarray[i]));
		}
	}
	
	boolean isConsonant(char c) {
		return consonants.contains(new Character(c));
	}
	
	Set<Character> consonants = new HashSet<Character>();

}
