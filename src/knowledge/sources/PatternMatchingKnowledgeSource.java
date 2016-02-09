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
import util.BlackboardUtil;
import util.SentenceUtil;

public class PatternMatchingKnowledgeSource extends WordKnowledgeSource {
	
	private static ArrayList<String> dict = new ArrayList<String>();
	
    @Override
    public String toString() {
        return "PatternMatchingKnowledgeSource";
    }
    
    public PatternMatchingKnowledgeSource() {
    	dict.clear();
    	try {
			Scanner s = new Scanner(new File("resources/words.txt"));
			while (s.hasNext()) dict.add(s.next().toUpperCase());
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    public void evaluate() {  	
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);

		for (int w = words.size() - 1; w > 0; w--) {
			List<CipherLetter> letters = SentenceUtil.getLetters(words.get(w));

			if (letters.size() < 4) continue;
			
			String regex = "";
			int unknownCount = 0;
			for (CipherLetter cl : BlackboardUtil.getCurrentSentenceState().get(w)) {
				if (cl.getAffirmations().getSolvedLetter().getPlainLetter() == null) {
					regex += ".";
					unknownCount++;
				}
				else {
					regex += cl.getAffirmations().getSolvedLetter().getPlainLetter();
				}
			}

			if (unknownCount == 0 || unknownCount > 2) continue;
						
			for (String dictWord : getWords(regex, 2)) {
				for (int i = 0; i < letters.size(); i++) {
					if (history.contains(letters.get(i).value())) continue;
					if (regex.charAt(i) != '.') continue;
					
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
    
    private static List<String> getWords(String regex, int numWords)  {
		List<String> ret = new ArrayList<String>();

		for (int i = 0; i < dict.size(); i++) {
			if (Pattern.matches(regex, dict.get(i)))
				ret.add(dict.get(i));
			if (ret.size() == numWords) break;
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		List<String> words = getWords(".HELL", 5);
		for (String s : words) {
			System.out.println(s);
		}
	}
  
}
