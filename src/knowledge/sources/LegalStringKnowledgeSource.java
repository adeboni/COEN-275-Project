package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import domain.Dependent.Direction;
import util.SentenceUtil;

public class LegalStringKnowledgeSource extends StringKnowledgeSource {

    private HashSet<String> illegalDict;
    
    public LegalStringKnowledgeSource() {
       illegalDict = new HashSet<String>();
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
        
        for (Word word : sentence.getWords()) {
           temp = "";
           
           for (CipherLetter letter : word.getLetters()) {
           
              if (letter.getAffirmations().cipherLetterHasAssumption()) {
                  temp += letter.getAffirmations().plainText();
                  
                  if (illegalDict.contains(temp)) { // should be part of temp is in illegalDict instead
                      assumption = letter.getAffirmations().mostRecent();
                      assumption.setRemoveFlag(true);
                      pastAssumptions.add(assumption);
                      assumption.notify(Direction.REVERSE, assumption);
                      history.add(temp);
                      return;
                  }
              } else {
                  temp = "";
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
          illegalDict.clear();
          s = new Scanner(new File("resources/illegalStrings.txt"));
          while (s.hasNext()) {
             illegalDict.add(s.next().toUpperCase());
          }
          s.close();
       } catch (FileNotFoundException e) {
          System.out.println(e);
       }
    }
}
