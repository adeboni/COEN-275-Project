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
import domain.Sentence;
import domain.Word;
import util.SentenceUtil;

public class DoubleLetterKnowledgeSource extends StringKnowledgeSource {

    @Override
    public String toString() {
        return "DoubleLetterKnowledgeSource";
    }

    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);
        
        
    }

    public List<String> doubleLetter(String word, int numWords) throws FileNotFoundException {
        //if Two back to back same letters in the middle of the a word are likely 2 vowels > "EE" or "OO" especially in a 4 char word
        ArrayList<String> dict = new ArrayList<>();
        List<String> ret = new ArrayList<String>();
        int i = 0;
        boolean found=false;
        for (i = 0; i < word.length()-1; i++) {
            if (word.charAt(i) == word.charAt(i+1)) {
                found=true;
                break;
            }
        }
        if(found) {
            Scanner s = new Scanner(new File("resources/doubleLetters.txt"));
            while (s.hasNext()) dict.add(s.next());
            s.close();
        }
        for (int k = 0; k < numWords; k++) {
            ret.add(dict.get(k));

    }
    return ret;
    }

    public static void main(String[] args) throws FileNotFoundException {
        DoubleLetterKnowledgeSource doubleLetterKnowledgeSource = new DoubleLetterKnowledgeSource();
        List<String> words = doubleLetterKnowledgeSource.doubleLetter("Apple", 5);
        for (String s : words) {
            System.out.println(s);
        }
    }
}
