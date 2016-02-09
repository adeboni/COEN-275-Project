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
import util.BlackboardUtil;
import util.SentenceUtil;

public class SolvedKnowledgeSource extends SentenceKnowledgeSource {
	private static ArrayList<String> dict = new ArrayList<String>();

    @Override
    public String toString() {
        return "SolvedKnowledgeSource";
    }
    
    public SolvedKnowledgeSource() {
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
        
        for (int w = 0; w < words.size(); w++) {
			List<CipherLetter> letters = BlackboardUtil.getCurrentSentenceState().get(w);
			String currWord = "";
			for (CipherLetter cl : letters) {
				if (cl.getAffirmations().getSolvedLetter().getPlainLetter() == null) return;
				currWord += cl.getAffirmations().getSolvedLetter().getPlainLetter();
			}
			
			if (!dict.contains(currWord)) return;
		}
        
        sentence.setSolved();
        
        queue.add(new Assumption());
        this.setPastAssumptions(queue);
    }
    
 
}    
