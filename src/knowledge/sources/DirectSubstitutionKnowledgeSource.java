package knowledge.sources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assertion;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import domain.Dependent.Direction;

public class DirectSubstitutionKnowledgeSource extends LetterKnowledgeSource {

	private final Map<String, String> substitutions = new HashMap<String, String>();
	
	public DirectSubstitutionKnowledgeSource() {
		substitutions.put("W", "V");
		
		/*
		Scanner in = new Scanner(System.in);
		while (true) {

			System.out.print("Enter a direct substitution in the form X->Y (or anything else to finish): ");
			
			try {
				String[] text = in.nextLine().split("->");
				substitutions.put(text[0], text[1]);
			}
			catch (Exception ex) {
				in.close();
				return;
			}
		
		}*/
		
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
        List<Word> words = sentence.getWords();

        for (Word word : words) {
            List<CipherLetter> letters = word.getLetters();

            for (CipherLetter letter : letters) {
                for (String cipher : substitutions.keySet()) {
                    if (letter.value().equals(cipher) && !history.contains(cipher)) {
                        history.add(cipher);
                        String plainText = substitutions.get(cipher);

                        Assertion assertion = new Assertion();
                        assertion.setCipherLetter(cipher);
                        assertion.setPlainLetter(plainText);

                        queue.add(assertion);
                        assertion.notify(Direction.FORWARD, assertion);
                        
                        System.out.println("The DirectSubstitutionKnowledgeSource made an assertion to change the letter " + cipher + " to letter " + plainText + ".");
                    }
                }
            }
        }
        this.setPastAssumptions(queue);
    }
}
