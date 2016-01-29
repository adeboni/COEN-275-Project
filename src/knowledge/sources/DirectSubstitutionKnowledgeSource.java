package knowledge.sources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Alphabet;
import domain.Assertion;
import domain.Assumption;
import domain.Sentence;
import domain.Word;
import util.SentenceUtil;

public class DirectSubstitutionKnowledgeSource extends LetterKnowledgeSource {

	private final Map<String, String> substitutions = new HashMap<String, String>();
	
	public DirectSubstitutionKnowledgeSource() {
		substitutions.put("V", "W");
	}
	
		
    @Override
    public String toString() {
        return "DirectSubstitutionKnowledgeSource";
    }

    @Override
    public void evaluate() {
        Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);

        for (Word word : words) {
        	List<Alphabet> letters = SentenceUtil.getLetters(word);

            for (Alphabet letter : letters) {
            	for (String cipher : substitutions.keySet()) {
	                if (letter.getCipherLetter().equals(cipher) && !history.contains(cipher)) {
	                	history.add(cipher);
	                	String plainText = substitutions.get(cipher);
	                    
	                    Assertion assertion = new Assertion();
	                    assertion.setCipherLetter(cipher);
	                    assertion.setPlainLetter(plainText);

	                    queue.add(assertion);
	                    this.setPastAssumptions(queue);
	                    
	                    System.out.println("The DirectSubstitutionKnowledgeSource made an assertion to change the letter " + cipher + " to letter " + plainText + ".");
	                    
	                    return;
	                }
            	}
            }
        }
        
        
    }


}
