package knowledge;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import domain.Assumption;

/**
 * Created by mickey.liu on 1/21/16.
 */
public class DoubleLetterKnowledgeSource extends StringKnowledgeSource {

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
