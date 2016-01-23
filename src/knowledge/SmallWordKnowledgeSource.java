package knowledge;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SmallWordKnowledgeSource extends WordStructureKnowledgeSource {

	public static List<String> getWords(int numLetters, int numWords) throws FileNotFoundException {
		List<String> ret = new ArrayList<String>();
		
		Scanner s = new Scanner(new File("resources/words.txt"));
		ArrayList<String> dict = new ArrayList<String>();
		while (s.hasNext()) dict.add(s.next());
		s.close();
		
		for (int i = 0; i < dict.size(); i++) {
			if (dict.get(i).length() == numLetters)
				ret.add(dict.get(i));
			if (ret.size() == numWords) break;
		}
			
		return ret;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		List<String> words = getWords(4, 10);
		for (String s : words) {
			System.out.println(s);
		}
	}

}
