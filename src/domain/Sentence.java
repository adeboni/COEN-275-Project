package domain;

import java.util.ArrayList;
import java.util.List;


public class Sentence extends BlackboardObject {

    /**
     * Collection of words that make up the sentence
     */
    protected List<Word> words = new ArrayList<Word>(); 

    /**
     * Attribute sentence
     */
    protected String sentence;

    /**
     * Default constructor
     */
    public Sentence() {
    }

    /**
     * Loaded constructor
     * 
     * @param sentence
     *            the sentence string
     */
    public Sentence(final String sentence) {
        this.sentence = sentence;
    }

    /**
     * Return the current value of of the sentence
     * 
     * @return the string value for the sentence
     */
    public String value() {
        return sentence;
    }

    /**
     * Public method to determine if the sentence has been solved
     * 
     * @return boolean true if solved
     */
    public boolean isSolved() {

        List<Word> list = this.getWords();

        for (Word word : list) {
            if (!word.isSolved()) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param words
     *            the words to set
     */
    public void setWords(List<Word> words) {
        this.words = words;
    }

    /**
     * @return the words
     */
    public List<Word> getWords() {
        return words;
    }

    /**
     * @return the sentence
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * @param sentence the sentence to set
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

}
