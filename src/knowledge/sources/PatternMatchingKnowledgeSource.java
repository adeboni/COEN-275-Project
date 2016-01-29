package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class PatternMatchingKnowledgeSource extends WordKnowledgeSource {
	
    @Override
    public String toString() {
        return "PatternMatchingKnowledgeSource";
    }


    @Override
    public void evaluate() {
        
    
    }
    
    public static List<String> getWords(String regex, int numWords) throws FileNotFoundException {
		List<String> ret = new ArrayList<String>();
		
		Scanner s = new Scanner(new File("resources/words.txt"));
		ArrayList<String> dict = new ArrayList<String>();
		while (s.hasNext()) dict.add(s.next());
		s.close();
		
		for (int i = 0; i < dict.size(); i++) {
			if (Pattern.matches(regex, dict.get(i)))
				ret.add(dict.get(i));
			if (ret.size() == numWords) break;
		}
			
		return ret;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		List<String> words = getWords("to.e.", 10);
		for (String s : words) {
			System.out.println(s);
		}
	}
  
}
