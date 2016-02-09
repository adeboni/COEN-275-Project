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

			if (letters.size() > 3) continue;
			
			for (String dictWord : getWords(letters.size(), 5)) {
				for (int i = 0; i < letters.size(); i++) {
					if (history.contains(letters.get(i).value())) continue;
					
					Assumption assumption = new Assumption();

					assumption.setCipherLetter(letters.get(i).value());
					assumption.setPlainLetter(Character.toString(dictWord.charAt(i)));
					
					assumption.addReference(this);
					assumption.notify(Direction.REVERSE, assumption);

					queue.add(assumption);
					
					history.add(letters.get(i).value());
				}
				
			}
		}
			
		this.setPastAssumptions(queue);
	}
        
    
    public List<String> getWords(int numLetters, int numWords) {
		List<String> ret = new ArrayList<String>();
		ArrayList<String> dict = new ArrayList<String>();
		
		try {
			Scanner s = new Scanner(new File("resources/words.txt"));
			while (s.hasNext()) dict.add(s.next().toUpperCase());
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).length() == numLetters)
				ret.add(dict.get(i));
			if (ret.size() == numWords) break;
		}
			
		return ret;
	}
	
	public static void main(String[] args) {
		SmallWordKnowledgeSource smallWordKnowledgeSource = new SmallWordKnowledgeSource();
		for (String s : smallWordKnowledgeSource.getWords(1, 10)) {
			System.out.println(s);
		}
	}

}
