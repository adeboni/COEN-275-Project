package domain;

public class CipherLetter extends BlackboardObject {

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
        // look for the latest assertion statement (near the top of the stack, 
        // the end of the vector) from affirmations
        for (int i = affirmations.getStatements().size(); i > 0; --i) {
            if (!affirmations.statementAt(i).isRetractable()) {
                return true;
            }
        }
        return false;
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
