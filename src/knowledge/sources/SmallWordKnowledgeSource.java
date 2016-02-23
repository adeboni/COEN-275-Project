package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import util.SentenceUtil;

public class SmallWordKnowledgeSource extends WordKnowledgeSource {

	private static ArrayList<String> dict = new ArrayList<String>();
	
    @Override
    public String toString() {
        return "SmallWordKnowledgeSource";
    }
    
    public SmallWordKnowledgeSource() {
    	dict.clear();
    	
    	try {
			Scanner s = new Scanner(new File("resources/words.txt"));
			while (s.hasNext()) dict.add(s.next().toUpperCase());
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);
        HashSet<String> addedLetters = new HashSet<String>();

		for (Word word : words) {
			List<CipherLetter> letters = SentenceUtil.getLetters(word);

			if (letters.size() > 3) continue;
			
			for (String dictWord : getWords(letters.size(), 5)) {
				for (int i = 0; i < letters.size(); i++) {
					if (addedLetters.contains(letters.get(i).value())) continue;
					if (history.containsKey(word.value() + letters.get(i).value())) {
						//if (history.get(word.value() + letters.get(i).value()).contains(Character.toString(dictWord.charAt(i))))
							//break;
					}
					
					Assumption assumption = new Assumption();
					assumption.setCipherLetter(letters.get(i).value());
					assumption.setPlainLetter(Character.toString(dictWord.charAt(i)));
					queue.add(assumption);
					
					addedLetters.add(letters.get(i).value());
					if (!history.containsKey(word.value() + letters.get(i).value()))
						history.put(word.value() + letters.get(i).value(), new HashSet<String>());
					history.get(word.value() + letters.get(i).value()).add(Character.toString(dictWord.charAt(i)));
				}
				
			}
		}
			
		this.setPastAssumptions(queue);
	}
        
    
    private static List<String> getWords(int numLetters, int numWords) {
		List<String> ret = new ArrayList<String>();
		
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).length() == numLetters)
				ret.add(dict.get(i));
			if (ret.size() == numWords) break;
		}
			
		return ret;
	}
	
	public static void main(String[] args) {
		for (String s : getWords(1, 10)) {
			System.out.println(s);
		}
	}

}
