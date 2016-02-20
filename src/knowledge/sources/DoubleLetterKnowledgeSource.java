package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import domain.Dependent.Direction;
import util.SentenceUtil;

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
        List<String> doubleLetters = doubleLetter();

        for (Word word : words) {
        	List<CipherLetter> letters = SentenceUtil.getLetters(word);
        	int index = 0;
            boolean found = false;
            for (int i = 0; i < letters.size() - 1; i++) {
                if (letters.get(i).value().equals(letters.get(i + 1).value())) {
                    found = true;
                    index = i;
                    break;
                }
            }

            if (found && !history.containsKey(letters.get(index).value())) {
            	for (String dl : doubleLetters) {
            		String firstLetterInMatch = dl.substring(0,1);

                    Assumption assumption = new Assumption();
                    assumption.setCipherLetter(letters.get(index).value());
                    assumption.setPlainLetter(firstLetterInMatch);
                    
                    letters.get(index).addReference(this);
                    letters.get(index).notify(Direction.REVERSE, assumption);
                    
                    queue.add(assumption);
                    history.put(letters.get(index).value(), new ArrayList<String>());
                    doubleLetters.remove(0);
                    break;

            	}
            }
        }

        this.setPastAssumptions(queue);
    }

    public List<String> doubleLetter() {
        // if Two back to back same letters in the middle of the a word are likely 2 vowels > "EE" or "OO" especially in a 4 char word

    	List<String> ret = new ArrayList<String>();

		for (int i = 0; i < dict.size(); i++) {
			ret.add(dict.get(i));
		}

        return ret;
    }
}


