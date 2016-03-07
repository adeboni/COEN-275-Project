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
import util.BlackboardUtil;
import util.SentenceUtil;
import util.WordRegex;

public class CommonSuffixKnowledgeSource extends StringKnowledgeSource {

    @Override
    public String toString() {
        return "CommonSuffixKnowledgeSource";
    }

    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);
        HashSet<String> addedLetters = new HashSet<String>();
        
        for (int w = 0; w < words.size(); w++) {
			List<CipherLetter> letters = SentenceUtil.getLetters(words.get(w));
			WordRegex wr = BlackboardUtil.getWordRegex(w);
			
			if (wr.unknownCount == 0) continue;
			
			String temp = new StringBuilder(wr.regex).reverse().toString();
			int length = 0;
			while (length < temp.length() && temp.charAt(length) == '.') length++;
        				
			if (length <= 2) continue;
									
			for (String dictWord : getSuffixes(length)) {
				boolean wholeWordGood = true;
				ConcurrentLinkedQueue<Assumption> tempQueue = new ConcurrentLinkedQueue<Assumption>();
				
				int delta = letters.size() - dictWord.length();
				for (int i = 0; i < dictWord.length(); i++) {
					if (addedLetters.contains(letters.get(i + delta).value())) continue;
					
					if (blackboard.checkPair(letters.get(i + delta).value(), dictWord.charAt(i)) || 
							blackboard.boardedPlainLetters.contains(dictWord.charAt(i))) {
						wholeWordGood = false;
						break;
					}
					
					System.out.println("CommonSuffix KS setting " + letters.get(i + delta).value() + " to " + dictWord.charAt(i));
					
					Assumption assumption = new Assumption();
					assumption.setCipherLetter(letters.get(i + delta).value());
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
    
    private static List<String> getSuffixes(int numLetters) {
		List<String> ret = new ArrayList<String>();
		
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).length() <= numLetters)
				ret.add(dict.get(i));
		}
			
		return ret;
	}
    
    public CommonSuffixKnowledgeSource() {
    	dict.clear();
    	
    	try {
			Scanner s = new Scanner(new File("resources/suffixes.txt"));
			while (s.hasNext()) dict.add(s.next().toUpperCase());
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    private static ArrayList<String> dict = new ArrayList<String>();
}
