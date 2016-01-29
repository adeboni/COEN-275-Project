package knowledge.sources;

import java.util.HashSet;
import java.util.Set;

public class VowelKnowledgeSource extends LetterKnowledgeSource {

    @Override
    public String toString() {
        return "VowelKnowledgeSource";
    }

    @Override
    public void evaluate() {
        // TODO Auto-generated method stub
    }
    
    public VowelKnowledgeSource() {
		char vowelarray[] = {'a', 'e', 'i', 'o', 'u'};
		for (int i = 0; i < vowelarray.length; ++i) {
		  vowels.add(new Character(vowelarray[i]));
		  vowels.add(Character.toUpperCase(vowelarray[i]));
		}
	}
	
	boolean isVowel(char c) {
		return vowels.contains(new Character(c));
	}
	
	Set<Character> vowels = new HashSet<Character>();

}
