package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
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
import util.BlackboardUtil;
import util.SentenceUtil;
import util.WordRegex;

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
        HashSet<String> addedLetters = new HashSet<String>();
        
		for (int w = 0; w < words.size(); w++) {
			List<CipherLetter> letters = SentenceUtil.getLetters(words.get(w));
			WordRegex wr = BlackboardUtil.getWordRegex(w);
			
			if (letters.size() < 4 || wr.unknownCount == 0 || wr.unknownCount > 2) continue;
									
			for (String dictWord : getWords(wr.regex)) {
				boolean wholeWordGood = true;
				ConcurrentLinkedQueue<Assumption> tempQueue = new ConcurrentLinkedQueue<Assumption>();
				
				for (int i = 0; i < letters.size(); i++) {
					if (addedLetters.contains(letters.get(i).value())) continue;
					if (wr.regex.charAt(i) != '.') continue;
					
					if (blackboard.checkPair(letters.get(i).value(), dictWord.charAt(i)) || 
							blackboard.boardedPlainLetters.contains(dictWord.charAt(i))) {
						wholeWordGood = false;
						break;
					}
					
					System.out.println("PatternMatching KS setting " + letters.get(i).value() + " to " + dictWord.charAt(i));
					
					Assumption assumption = new Assumption();
					assumption.setCipherLetter(letters.get(i).value());
					assumption.setPlainLetter(Character.toString(dictWord.charAt(i)));
					tempQueue.add(assumption);
					
					addedLetters.add(letters.get(i).value());
					blackboard.boardedPlainLetters.add(Character.toString(dictWord.charAt(i)));
				}
				
				if (wholeWordGood) {
					queue.addAll(tempQueue);
					break;
				}
			}
		}
			
		this.setPastAssumptions(queue);
	}
    
    private static List<String> getWords(String regex)  {
		List<String> ret = new ArrayList<String>();

		for (int i = 0; i < dict.size(); i++) {
			if (Pattern.matches(regex, dict.get(i)))
				ret.add(dict.get(i));
		}
		
		return ret;
	}
	
	public static void main(String[] args) {
		new PatternMatchingKnowledgeSource();
		List<String> words = getWords("[IHAVESNTML]{3}");
		for (String s : words) {
			System.out.println(s);
		}
	}
  
}
