package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import domain.Dependent.Direction;
import util.SentenceUtil;

public class PatternMatchingKnowledgeSource extends WordKnowledgeSource {
	
    @Override
    public String toString() {
        return "PatternMatchingKnowledgeSource";
    }


    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);

		for (Word word : words) {
			List<CipherLetter> letters = SentenceUtil.getLetters(word);
			//somehow get solved letters and build the regex
			
			/*
			for (String dictWord : getWords(".*", 5)) {
				for (int i = 0; i < letters.size(); i++) {
					if (history.contains(letters.get(i).value())) continue;
					
					Assumption assumption = new Assumption();

					assumption.setCipherLetter(letters.get(i).value());
					assumption.setPlainLetter(dictWord.charAt(i) + "");
					
					assumption.addReference(this);
					assumption.notify(Direction.REVERSE, assumption);

					queue.add(assumption);
					
					history.add(letters.get(i).value());
				}
				
			}*/
		}
			
		this.setPastAssumptions(queue);
	}
    
    public static List<String> getWords(String regex, int numWords)  {
		List<String> ret = new ArrayList<String>();
		
		try {
			Scanner s = new Scanner(new File("resources/words.txt"));
			ArrayList<String> dict = new ArrayList<String>();
			while (s.hasNext()) dict.add(s.next().toUpperCase());
			s.close();
			
			for (int i = 0; i < dict.size(); i++) {
				if (Pattern.matches(regex, dict.get(i)))
					ret.add(dict.get(i));
				if (ret.size() == numWords) break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		return ret;
	}
	
	public static void main(String[] args) {
		List<String> words = getWords("...LL", 5);
		for (String s : words) {
			System.out.println(s);
		}
	}
  
}
