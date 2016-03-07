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

public class DoubleLetterKnowledgeSource extends StringKnowledgeSource {

	private static ArrayList<String> dict = new ArrayList<String>();
	
    @Override
    public String toString() {
        return "DoubleLetterKnowledgeSource";
    }
    
    public DoubleLetterKnowledgeSource() {
    	dict.clear();
    	try {
			Scanner s = new Scanner(new File("resources/doubleLetters.txt"));
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

        for (int w = 0; w < words.size(); w++) {
			List<CipherLetter> letters = SentenceUtil.getLetters(words.get(w));
			WordRegex wr = BlackboardUtil.getWordRegex(w);
			
			if (wr.unknownCount == 0) continue;
			
			int index = -1;
            for (int i = 0; i < letters.size() - 1; i++) {
                if (letters.get(i).value().equals(letters.get(i + 1).value())) {
                    index = i;
                    break;
                }
            }
			
			if (index < 0 || !wr.regex.substring(index, index + 2).equals("..") || addedLetters.contains(letters.get(index).value())) continue;
									
			for (Character doubleLetter : doubleLetter()) {
				if (blackboard.checkPair(letters.get(index).value(), doubleLetter) || blackboard.boardedPlainLetters.contains(doubleLetter.toString())) 
					continue;
				
				System.out.println("DoubleLetter KS setting " + letters.get(index).value() + " to " + doubleLetter);
				
				Assumption assumption = new Assumption();
				assumption.setCipherLetter(letters.get(index).value());
				assumption.setPlainLetter(doubleLetter.toString());
				queue.add(assumption);
				
				addedLetters.add(letters.get(index).value());
				blackboard.boardedPlainLetters.add(doubleLetter.toString());
				break;
			}
		}
        
		this.setPastAssumptions(queue);
	}

    public List<Character> doubleLetter() {
    	List<Character> ret = new ArrayList<Character>();
    	
		for (int i = 0; i < dict.size(); i++) 
			ret.add(dict.get(i).charAt(0));
		
        return ret;
    }
}


