package knowledge.sources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import blackboard.Blackboard;
import blackboard.BlackboardContext;
import com.sun.xml.internal.bind.v2.TODO;
import domain.Assumption;
import domain.Sentence;
import domain.Word;
import util.SentenceUtil;

public class SentenceStructureKnowledgeSource extends SentenceKnowledgeSource {

    private HashMap<WordType, List<String>> masterWordList = new HashMap<WordType, List<String>>();;
    private ArrayList<String> dict = new ArrayList<String>();

    public SentenceStructureKnowledgeSource() {
        masterWordList.put(WordType.ADJECTIVE, readFiles(new File("resources/words_adjectives.txt")));
        masterWordList.put(WordType.NOUN, readFiles(new File("resources/words_nouns.txt")));
        masterWordList.put(WordType.ARTICLE, readFiles(new File("resources/words_articles.txt")));
        masterWordList.put(WordType.PREPOSITION, readFiles(new File("resources/words_prepositions.txt")));
        masterWordList.put(WordType.VERB, readFiles(new File("resources/words_verbs.txt")));
        masterWordList.put(WordType.PRONOUN, readFiles(new File("resources/words_pronouns.txt")));

    }

    public List<String> readFiles(File file) {
        dict.clear();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) dict.add(s.next().toUpperCase());
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return dict;
    }



    @Override
    public String toString() {
        return "SentenceStructureKnowledgeSource";
    }

    @Override
    public void evaluate() {
    	Blackboard blackboard = BlackboardContext.getInstance().getBlackboard();
        Sentence sentence = blackboard.getSentence();
        ConcurrentLinkedQueue<Assumption> queue = this.getPastAssumptions();
        List<Word> words = SentenceUtil.getWords(sentence);

        for (int i=0; i<words.size(); i++) {
            if (getWordType(words.get(i)) != null) {
            //process

        /*TODO implement Sentence rules.
        1. Cannot have article + verb
        2. pronoun must be followed by verb
        3. For each word and word + 1, assess grammar rules
*/
            }

        }

    }
    private enum WordType {
        ADJECTIVE, NOUN, ARTICLE, PREPOSITION, PRONOUN, VERB, ADVERB
    }

    public WordType getWordType(Word w) {
        WordType finalType = null;
        for (WordType wt : masterWordList.keySet()) {
            List<String> dict = masterWordList.get(wt);
            if (dict.contains(w)) {
                finalType = wt;
                break;

            }
        }
        return finalType;

    }



    }

