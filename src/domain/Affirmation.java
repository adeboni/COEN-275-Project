package domain;

import java.util.Stack;

public class Affirmation {

    /**
     * The cipher letter relating to this affirmation
     */
    private CipherLetter _cipherLetter;

    /**
     * The plaintext equivalent relating to this affirmation. We "think" this is
     * the logical letter replacement or solution for the ciphertext letter
     */
    private Alphabet _solvedLetter;

    /**
     * Our stack of assumptions. Remember that assertions extend assumption
     */
    private Stack<Assumption> _assumptions = new Stack<Assumption>();

    /**
     * Default constructor
     */
    public Affirmation() {
    }

    public void push(Assumption assumption) {
        _assumptions.push(assumption);
    }

    /**
     * Retract an Assumption
     * 
     */
    public Assumption pop() {
        return _assumptions.pop();
    }

    /**
     * Get Assumption
     * @param i
     * @return
     */
    public Assumption statementAt(int i) {
        return _assumptions.get(i);
    }
    /**
     * Public method to get ciphertext (rarely, may not be finished)
     * 
     * @return String
     */
    public String cipherText() {
        return _cipherLetter.value();
    }

    /**
     * Public method to get solved letter (for display of our work)
     * 
     * @return String
     */
    public String plainText() {
        return _solvedLetter.getPlainLetter();
    }

    /**
     * Public method to determine if plain letter has been asserted
     * 
     * @return boolean
     */
    public boolean isPlainLetterAsserted() {
        boolean result = false;

        Stack<Assumption> stack = this._solvedLetter.getAffirmations().getStatements();
        for (int i = 0; i < stack.size(); i++) {
            Assumption assumption = stack.pop();
            if (!assumption.isRetractable()) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Public method to determine if cipher letter has been asserted
     * 
     * @return boolean
     */
    public boolean isCipherLetterAsserted() {

        boolean result = false;

        Stack<Assumption> stack = this._cipherLetter.getAffirmations().getStatements();
        for (int i = 0; i < stack.size(); i++) {
            Assumption assumption = stack.pop();
            if (!assumption.isRetractable()) {
                result = true;
            }
        }

        return result;
    }

    /**
     * Public method to determine if an assumption is made against the plain
     * letter
     * 
     * @return boolean
     */
    public boolean plainLetterHasAssumption() {
        return this._solvedLetter.getAffirmations().getStatements().size() > 0;
    }

    /**
     * Public method to determine if an assumption is made against the cipher
     * letter
     * 
     * @return boolean
     */
    public boolean cipherLetterHasAssumption() {
        return this._cipherLetter.getAffirmations().getStatements().size() > 0;
    }

    /**
     * @return {@link CipherLetter}
     */
    public CipherLetter getCipherLetter() {
        return _cipherLetter;
    }

    /**
     * @param cipherLetter
     *   the {@link CipherLetter} to set
     */
    public void setCipherLetter(CipherLetter cipherLetter) {
        this._cipherLetter = cipherLetter;
    }

    /**
     * @return {@link Alphabet}
     */
    public Alphabet getSolvedLetter() {
        return _solvedLetter;
    }

    /**
     * @param solvedLetter
     *            the solvedLetter to set
     */
    public void setSolvedLetter(Alphabet solvedLetter) {
        this._solvedLetter = solvedLetter;
    }

    /**
     * @return Stack<Assumption>
     */
    public Stack<Assumption> getStatements() {
        return _assumptions;
    }

    /**
     * @param statements
     *            the statements to set
     */
    public void setStatements(Stack<Assumption> statements) {
        this._assumptions = statements;
    }

    public boolean hasAssumption() {
        return _assumptions.size() > 0;
    }
}
