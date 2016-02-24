package domain;

import java.util.ArrayList;
import java.util.List;

public class Word extends BlackboardObject {

    /**
     * List of letters that make up this word
     */
    private List<CipherLetter> letters = new ArrayList<CipherLetter>();

    /**
     * The string word
     */
    private String word;
    
    /**
     * Word before
     */
    private Word previousWord;
    
    /**
     * Word after
     */
    private Word nextWord;

    /**
     * Loaded constructor
     * 
     * @param word
     */
    public Word(final String word) {
        this.word = word;
    }

    /**
     * Public method to return the value or the string word
     * 
     * @return word as String
     */
    public String value() {
        return word;
    }

    /**
     * Public method to determine if the word has been solved
     * 
     * @return boolean true if solved
     */
    public boolean isSolved() {
        for (CipherLetter letter : letters) {
           if (!letter.isSolved()) {
              return false;
           }
        }
        
        return true;
    }

    /**
     * Public setter for list of cipher letters
     * 
     * @param letters
     */
    public void setLetters(List<CipherLetter> letters) {
        this.letters = letters;
    }

    /**
     * Public getter for list of cipher letters
     * 
     * @return list of cipher letters
     */
    public List<CipherLetter> getLetters() {
        return letters;
    }

    /**
     * @return the previousWord
     */
    public Word getPreviousWord() {
        return previousWord;
    }

    /**
     * @param previousWord the previousWord to set
     */
    public void setPreviousWord(Word previousWord) {
       this.previousWord = previousWord;
    }

    /**
     * @return the nextWord
     */
    public Word getNextWord() {
        return nextWord;
    }

    /**
     * @param nextWord the nextWord to set
     */
    public void setNextWord(Word nextWord) {
        this.nextWord = nextWord;
    }
    
    

}
