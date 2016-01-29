package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

        /**
         * Always use a local result
         */
        boolean result = false;

        /**
         * No. of true occurrences
         */
        int countTrue = 0;

        /**
         * Iterate over all letters, be sure there is an Assertion for each
         */
        List<CipherLetter> list = this.getLetters();
        int count = list.size();

        for (CipherLetter letter : list) {
            Stack<Assumption> stack = letter.getAffirmations().getStatements();
            for (int i = 0; i < stack.size(); i++) {
                Assumption assumption = stack.pop();
                if (!assumption.isRetractable()) {
                    countTrue++;
                }
            }
        }

        /**
         * If the no. of true occurrences equals the no. of letters it's solved
         */
        if (count == countTrue) {
            result = true;
        }

        return result;
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
