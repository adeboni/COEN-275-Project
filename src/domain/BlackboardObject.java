package domain;

public abstract class BlackboardObject extends Dependent {

    /**
     * Each blackboard object adds itself or registers with the blackboard. Each
     * object also represents itself within the blackboard domain.
     */
    public final void register() {
        // TODO
        // get blackboard
        // blackboard.add(this);
    }

    /**
     * Each blackboard object can also remove itself or resign from the problem
     * domain, e.g. {@link CipherLetter} objects can remove themselves and allow
     * an {@link Alphabet} or plaintext equivalent to be its representative
     */
    public final void resign() {
        // TODO
        // get blackboard
        // blackboard.remove(this);
     }
}
