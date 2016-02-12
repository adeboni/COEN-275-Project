package blackboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import domain.Affirmation;
import domain.Alphabet;
import domain.Assumption;
import domain.BlackboardObject;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;
import knowledge.KnowledgeSource;
import util.BlackboardUtil;
import util.SentenceUtil;
import util.StringTrimmer;

public class Blackboard extends ArrayList<BlackboardObject> {

    /**
    * Generated serialID
    */
    private static final long serialVersionUID = -8344157775909530435L;


    /**
     * Reset the blackboard
     */
    public final void reset() {
        this.clear();
    }

    /**
     * Public method used to determine if the blackboard problem is solved
     * 
     * @return boolean primitive
     */
    public final boolean isSolved() {
        Sentence sentence = getSentence();

        /**
         * If we have a sentence and the sentence is solved, then the blackboard
         * problem is solved.
         */
        if (sentence != null) {
            return sentence.isSolved();
        }
        return false;
    }

    public final void assertProblem(String code) {
        Sentence sentence = new Sentence(StringTrimmer.trim(code));
        buildSentenceGraph(sentence);
        registerBlackboardObjects(sentence);
    }

    private void buildSentenceGraph(Sentence sentence) {
        List<Word> words = SentenceUtil.getWords(sentence);
        sentence.setWords(words);

        for (Word word : words) {
            word.setLetters(SentenceUtil.getLetters(word));
        }
    }

    /**
     * @param sentence
     */
    private void registerBlackboardObjects(Sentence sentence) {
        sentence.register();
        List<Word> words = sentence.getWords();
        for (Word word : words) {
            word.register();
            for (CipherLetter letter : word.getLetters()) {
                letter.register();
            }
        }
    }

    /**
     * Public method allowing expert (knowledge source) a chance at the
     * blackboard model to evaluate the problem domain
     * 
     * @param ks
     *            the {@link knowledge.KnowledgeSource} at
     *            the board (presently)
     */
    public final void connect(KnowledgeSource ks) {

        ConcurrentLinkedQueue<Assumption> queue = ks.getPastAssumptions();

        if (queue.size() > 0) {

            Assumption assumption = queue.peek();

            /**
             * Search the ArrayList for our one and only sentence
             */
            Sentence sentence = getSentence();

            /**
             * make affirmation statement on letter-stack (push assumptions)
             */
            updateAffirmations(sentence, assumption);
        }

        BlackboardUtil.outputProgress(this);
    }

    /**
     * Private method to update blackboard with a new Alphabet when an assertion
     * is given for a particular cipher letter. Also register the assertion
     * (assumption) with the blackboard as well.
     * 
     * @param sentence
     * @param assumption
     */
    private void updateAffirmations(Sentence sentence, Assumption assumption) {
        assumption.register();

        for (Word word : sentence.getWords()) {
            for (CipherLetter cipherLetter : word.getLetters()) {
                Affirmation affirmation = cipherLetter.getAffirmations();
                Alphabet alphabet = affirmation.getSolvedLetter();
                
                if (!assumption.isValid() && affirmation.hasAssumption() && assumption.equals(affirmation.mostRecent())) {
                    // TODO: do not pop this and need to update the PlainLetter properly
                    affirmation.pop();
                    alphabet.setPlainLetter(null);

                } else if (cipherLetter.value().equals(assumption.getCipherLetter())) {
                    alphabet = cipherLetter.getAffirmations().getSolvedLetter();
                    alphabet.setPlainLetter(assumption.getPlainLetter());

                    System.out.println("updateAffirmations-> " + alphabet.getCipherLetter() + "->" + alphabet.getPlainLetter());
                    
                    affirmation.push(assumption);
                }
            }
        }
    }

    /**
     * Public method to removing expert (knowledge source) from the blackboard
     * model
     * 
     * @param ks
     *            the {@link knowledge.KnowledgeSource}
     *            steps back from the board
     */
    public final void disconnect(KnowledgeSource ks) {
    	ks.getPastAssumptions().remove(); //TODO: this might not be right
    }

    /**
     * Public method to get the problem domain sentence
     * 
     * @return String sentence
     */
    public Sentence getSentence() {

        Sentence sentence = null;

        /**
         * Search the ArrayList for our one and only sentence
         */
        for (BlackboardObject obj : this) {
            if (obj.getClass().equals(domain.Sentence.class)) {
                sentence = (Sentence) obj;
            }
        }

        return sentence;
    }

    
    /**
     * This method returns true if a cipher letter exists on the blackboard.
     * 
     * @param letter
     *            the String letter
     * @return a boolean if exists
     */
    public boolean cipherLetterExists(String letter) {
        for (BlackboardObject obj : this) {
            if (obj.getClass().equals(domain.CipherLetter.class)) {
                CipherLetter cipherLetter = (CipherLetter) obj;
                if (cipherLetter.value().equals(letter)) {
                    return true;
                }
            }
        }
        
        return false;
    }

}
