package domain;

public class Alphabet extends BlackboardObject {

    /**
     * Affirmation (statements) made against this alphabet
     */
    protected Affirmation affirmations = new Affirmation();
	
	/**
     * Attribute cipher letter knowledge
     */
    private String cipherLetter;

    /**
     * Attribute plaintext letter knowledge
     */
    private String plainLetter;
    
    private boolean vowel = false;
    
    private boolean consonant = false;
    
    /**
     * Constructor
     * 
     * @param cipherLetter
     */
    public Alphabet(final String cipherLetter) {
        this.cipherLetter = cipherLetter;
    }

    public boolean isAsserted() {
       for (int i = affirmations.getStatements().size() - 1; i >= 0; --i) {
          if (!affirmations.statementAt(i).isRetractable()) {
              return true;
          }
       }
      
      return false;
    }

	/**
	 * @return the vowel
	 */
	public boolean isVowel() {
		return vowel;
	}

	/**
	 * @param vowel the vowel to set
	 */
	public void setVowel(boolean vowel) {
		this.vowel = vowel;
	}

	/**
	 * @return the consonant
	 */
	public boolean isConsonant() {
		return consonant;
	}

	/**
	 * @param consonant the consonant to set
	 */
	public void setConsonant(boolean consonant) {
		this.consonant = consonant;
	}
    /**
     * @return the cipherLetter
     */
    public String getCipherLetter() {
        return cipherLetter;
    }

    /**
     * @param cipherLetter
     *            the cipherLetter to set
     */
    public void setCipherLetter(String cipherLetter) {
        this.cipherLetter = cipherLetter;
    }

    /**
     * @return the plainLetter
     */
    public String getPlainLetter() {
        return plainLetter;
    }

    /**
     * @param plainLetter
     *            the plainLetter to set
     */
    public void setPlainLetter(String plainLetter) {
        this.plainLetter = plainLetter;
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
