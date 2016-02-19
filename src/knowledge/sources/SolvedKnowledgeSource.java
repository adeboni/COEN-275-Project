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
        
        //ask user if it's good
        System.out.println("Does the following sentence make sense? (y/n)");
        for (int w = 0; w < words.size(); w++) {
        	for (CipherLetter l : BlackboardUtil.getCurrentSentenceState().get(w)) 
        		System.out.print(l.getAffirmations().getSolvedLetter().getPlainLetter());
        	System.out.print(" ");
        }
        System.out.print("\n");
        Scanner in = new Scanner(System.in);
        String answer = in.next();
        in.close();
        if (!answer.equals("y")) return;
        
        sentence.setSolved();
        
        queue.add(new Assumption());
        this.setPastAssumptions(queue);
    }
    
 
}    
