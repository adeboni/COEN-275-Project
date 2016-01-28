package knowledge;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import domain.Assumption;

public class WordStructureKnowledgeSource extends WordKnowledgeSource {

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


	@Override
	public void evaluate() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ConcurrentLinkedQueue<Assumption> getPastAssumptions() {
		// TODO Auto-generated method stub
		return null;
	}

}
