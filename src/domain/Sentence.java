package domain;

import java.util.List;

public class Sentence extends BlackboardObject {

    /**
     * Collection of words that make up the sentence
     */
    protected List<Word> words;

    /**
     * Attribute sentence
     */
    protected String sentence;

    /**
     * Default constructor
     */
    public Sentence() {
        this("");
    }

    /**
     * Loaded constructor
     * 
     * @param sentence
     *            the sentence string
     */
    public Sentence(String sentence) {
        this.sentence = sentence;
        words = null;
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
        for (Word w: words) {
            if (!w.isSolved()) {
                return false;
            }
        }
        return true;
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
     * @param words
     *            the words to set
     */
    public void setWords(List<Word> words) {
        this.words = words;
    }

    /**
     * @param sentence
     *            the sentence to set
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
