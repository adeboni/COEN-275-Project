package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assertion;
import domain.Assumption;
import domain.Sentence;
import domain.Word;
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
        
        //TODO: remove this, this is just for demonstration
        for (Word word : words) {
        	if (word.value().equals("DSSC")) {               
                Assertion assertion1 = new Assertion();
                assertion1.setCipherLetter("D");
                assertion1.setPlainLetter("S");
                queue.add(assertion1);
                
                Assertion assertion2 = new Assertion();
                assertion2.setCipherLetter("S");
                assertion2.setPlainLetter("E");
                queue.add(assertion2);
                
                Assertion assertion3 = new Assertion();
                assertion3.setCipherLetter("C");
                assertion3.setPlainLetter("N");
                queue.add(assertion3);
        	}
        }
        
        this.setPastAssumptions(queue);
    }
    
    public static List<String> getWords(int numLetters, int numWords) throws FileNotFoundException {
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
		List<String> words = getWords(4, 10);
		for (String s : words) {
			System.out.println(s);
		}
	}

}
