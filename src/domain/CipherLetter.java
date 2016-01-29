package domain;

public class CipherLetter extends BlackboardObject {

	private boolean _solved;

	private String cipherLetter;
	
    /**
     * Affirmation (statements) made against this cipher
     */
    protected Affirmation affirmations = new Affirmation();
	
    public String value() {
        return cipherLetter;
    } 
    
    public CipherLetter(String letter) {
    	cipherLetter = letter;
    }

    /**
     * @return the solved
     */
    public boolean isSolved() {
        return _solved;
    }

    /**
     * @param solved the solved to set
     */
    public void setSolved(boolean solved) {
        this._solved = solved;
    }

    /**
     * @return the affirmations
     */
    public Affirmation getAffirmations() {
        return affirmations;
    }

    /**
     * @param affirmations
     *            the affirmations to set
     */
    public void setAffirmation(Affirmation affirmation) {
        this.affirmations = affirmation;
    }

}
