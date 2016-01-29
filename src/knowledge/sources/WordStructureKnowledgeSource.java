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

public class WordStructureKnowledgeSource extends WordKnowledgeSource {

    @Override
    public String toString() {
        return "WordStructureKnowledgeSource";
    }
 
    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);
        
        
    }

    public static enum StructureLocation {
		PREFIX,
		SUFFIX
	}

	public List<String> getWords(StructureLocation location, int numLetters, int numWords) throws FileNotFoundException {
		List<String> ret = new ArrayList<String>();
		
		ArrayList<String> dict = new ArrayList<>();
		if (location == StructureLocation.PREFIX) {
			Scanner s = new Scanner(new File("resources/prefixes.txt"));
			while (s.hasNext()) dict.add(s.next());
			s.close();
		} else {
			Scanner s = new Scanner(new File("resources/suffixes.txt"));
			while (s.hasNext()) dict.add(s.next());
			s.close();
		}
		
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).length() == numLetters)
				ret.add(dict.get(i));
			if (ret.size() == numWords) break;
		}
			
		return ret;
	}


	public static void main(String[] args) throws FileNotFoundException {
		WordStructureKnowledgeSource wordStructureKnowledgeSource = new WordStructureKnowledgeSource();
		List<String> words = wordStructureKnowledgeSource.getWords(StructureLocation.PREFIX, 3, 20);
		for (String s : words) {
			System.out.println(s);
		}
	}
}
