package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.*;
import domain.Dependent.Direction;
import util.SentenceUtil;

public class SmallWordKnowledgeSource extends WordKnowledgeSource {

    @Override
    public String toString() {
        return "SmallWordKnowledgeSource";
    }

    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);

		for (Word word : words) {
			List<CipherLetter> letters = SentenceUtil.getLetters(word);

			if (letters.size() == 1 && !history.contains(word.value())) {
				Assumption assumption = new Assumption();

				assumption.setCipherLetter(word.value());
				assumption.setPlainLetter("I");

				queue.add(assumption);

				
				Assumption assumption2 = new Assumption();

				assumption2.setCipherLetter(word.value());
				assumption2.setPlainLetter("A");

				queue.add(assumption2);
				
				
				assumption.notify(Direction.REVERSE, assumption2);
				
				history.add(word.value());
			}
		}


        //TODO: remove this, this is just for demonstration
		/*{
        	if (!history.contains("D")) {
                Assertion assertion1 = new Assertion();
                assertion1.setCipherLetter("D");
                assertion1.setPlainLetter("S");
                queue.add(assertion1);
                history.add("D");

                Assertion assertion2 = new Assertion();
                assertion2.setCipherLetter("S");
                assertion2.setPlainLetter("E");
                queue.add(assertion2);
                history.add("S");

                Assertion assertion3 = new Assertion();
                assertion3.setCipherLetter("C");
                assertion3.setPlainLetter("N");
                queue.add(assertion3);
                history.add("C");
                
                
                
                
                
                Assertion assertion4 = new Assertion();
                assertion4.setCipherLetter("K");
                assertion4.setPlainLetter("T");
                queue.add(assertion4);
                history.add("K");

                Assertion assertion5 = new Assertion();
                assertion5.setCipherLetter("A");
                assertion5.setPlainLetter("H");
                queue.add(assertion5);
                history.add("A");
        	}
		}
		*/
		
		this.setPastAssumptions(queue);
	}
        


    
    public List<String> getWords(int numLetters, int numWords) throws FileNotFoundException {
		List<String> ret = new ArrayList<String>();
		
		Scanner s = new Scanner(new File("resources/words.txt"));
		ArrayList<String> dict = new ArrayList<String>();
		while (s.hasNext()) dict.add(s.next());
		s.close();
		
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).length() == numLetters)
				ret.add(dict.get(i));
			if (ret.size() == numWords) break;
		}
			
		return ret;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		SmallWordKnowledgeSource smallWordKnowledgeSource = new SmallWordKnowledgeSource();
		for (String s : smallWordKnowledgeSource.getWords(1, 10)) {
			System.out.println(s);
		}
	}

}
