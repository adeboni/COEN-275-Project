package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import domain.Dependent.Direction;

public class LegalStringKnowledgeSource extends StringKnowledgeSource {

    private HashSet<String> dict;
    
    public LegalStringKnowledgeSource() {
        dict = new HashSet<String>();
        reset();
    }

    @Override
    public String toString() {
        return "LegalStringKnowledgeSource";
    }

    @Override
    public void evaluate() {
        Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        Assumption assumption;
        String temp;
        List<CipherLetter> letters = new ArrayList<CipherLetter>();
        
        for (Word word : sentence.getWords()) {
            temp = "";
            letters.clear();
           
            for (CipherLetter letter : word.getLetters()) {
                if (letter.getAffirmations().cipherLetterHasAssumption()) {
                    temp += letter.getAffirmations().plainText();
                    letters.add(letter);
                    
                    for (int i = 0; i < temp.length() - 1; ++i) {
                        if (dict.contains(temp.substring(i, i + 2))) {
                            // TODO: still have to pick the latest assumption among these 2 letters
                            assumption = letter.getAffirmations().mostRecent();
                            assumption.setValidFlag(false);
                            pastAssumptions.add(assumption);
                            assumption.notify(Direction.REVERSE, assumption);
                            return;
                        }
                    }
                } else {
                    temp = "";
                    letters.clear();
                }
            }
        }
    }

    @Override
    public void reset() {
        super.reset();
        loadFile();
    }

    private void loadFile() {
        Scanner s;
       
        try {
            dict.clear();
            s = new Scanner(new File("resources/illegalStrings.txt"));
            while (s.hasNext()) {
                dict.add(s.next().toUpperCase());
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
