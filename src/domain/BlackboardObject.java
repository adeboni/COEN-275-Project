package domain;


public abstract class BlackboardObject {

    /**
     * Each blackboard object adds itself or registers with the blackboard. Each
     * object also represents itself within the blackboard domain.
     */
    public final void register() {
        
        
    }

    /**
     * Each blackboard object can also remove itself or resign from the problem
     * domain, e.g. {@link CipherLetter} objects can remove themselves and allow
     * an {@link Alphabet} or plaintext equivalent to be its representative
     */
    public final void resign() {
        
        
    }

}
