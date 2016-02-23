package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Affirmation;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import util.BlackboardUtil;
import util.SentenceUtil;

public class SolvedKnowledgeSource extends SentenceKnowledgeSource {
	private static ArrayList<String> dict = new ArrayList<String>();
	private static Scanner in = new Scanner(System.in);
	
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
        	System.out.print("(" +  w + ")");
        	for (CipherLetter l : BlackboardUtil.getCurrentSentenceState().get(w)) 
        		System.out.print(l.getAffirmations().getSolvedLetter().getPlainLetter());
        	System.out.print(" ");
        }
        System.out.print("\n");
        
        String answer = in.next();
        if (!answer.equals("y")) {
        	System.out.println("Which word is wrong?");
        	int n = in.nextInt();
        	System.out.println("Which letter(s) are wrong? (Comma separated)");
        	List<CipherLetter> ls = BlackboardUtil.getCurrentSentenceState().get(n);
        	for (int i = 0; i < ls.size(); i++) 
        		System.out.print("(" + i + ")" + ls.get(i).getAffirmations().getSolvedLetter().getPlainLetter() + " ");
        	System.out.print("\n");
        	String[] wrongLetters = in.next().split(",");
        	int[] wrongIndices = new int[wrongLetters.length];
        	for (int i = 0; i < wrongLetters.length; i++) 
        		wrongIndices[i] = Integer.parseInt(wrongLetters[i]);
        	
        	for (int index : wrongIndices) {
        		//go through these cipherletters and mark them as not solved
        		ls.get(index).setAffirmation(new Affirmation(ls.get(index)));
        	}
        	
        } else {
	        sentence.setSolved();
        }
        
        queue.add(new Assumption());
        this.setPastAssumptions(queue);
    }
    
 
}    
