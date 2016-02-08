package knowledge.sources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import domain.Assertion;
import domain.Assumption;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import domain.Dependent.Direction;
import util.SentenceUtil;

public class DoubleLetterKnowledgeSource extends StringKnowledgeSource {

    @Override
    public String toString() {
        return "DoubleLetterKnowledgeSource";
    }

    @Override
    public void evaluate() {
        Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);
        
        List<CipherLetter> letters;
        String wordStr;

        boolean found;
        int index;
        Assertion a;

        // demo only
        for (Word word : words) {
            letters = SentenceUtil.getLetters(word);
            wordStr = word.value();
            index = 0;
            found = false;
            for (int i = 0; i < wordStr.length() - 2; ++i) {
                if (wordStr.charAt(i) == wordStr.charAt(i + 1)) {
                    found = true;
                    index = i;
                    break;
                }
            }

            if (found) {
                if (index != 0) { // middle
                    String firstLetterInMatch = null;
                    try {
                        firstLetterInMatch = doubleLetter().substring(0,1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Assumption assumption = new Assumption();
                    assumption.setCipherLetter(Character.toString(wordStr.charAt(index)));
                    assumption.setPlainLetter(firstLetterInMatch);
                    
                    queue.add(assumption);
                    history.add(word.value());
                }
            }
        }

        setPastAssumptions(queue);
    }

    public String doubleLetter() throws FileNotFoundException {
        // if Two back to back same letters in the middle of the a word are likely 2 vowels > "EE" or "OO" especially in a 4 char word

        ArrayList<String> dict = new ArrayList<>();
        String doubleLetterString = null;

        Scanner s = new Scanner(new File("resources/doubleLetters.txt"));
        while (s.hasNext()) dict.add(s.next());
        s.close();

        //read dict ArrayList, if current line does not contain used char, return current line and break.

        for (String st : dict) {
            if (!history.contains(st.substring(0))) {
                doubleLetterString = st;
                break;
            }
        }

        return doubleLetterString.toUpperCase();
    }
}


