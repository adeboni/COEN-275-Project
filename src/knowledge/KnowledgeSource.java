package knowledge;

import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import domain.Assumption;

public abstract class KnowledgeSource {
	
	protected HashSet<String> history = new HashSet<String>();

    /**
     * Attribute queue of assumptions made by KnowledgeSource
     */
    protected ConcurrentLinkedQueue<Assumption> pastAssumptions = new ConcurrentLinkedQueue<Assumption>();
    
    /**
     * Evaluate sentence and provide expertise
     * @param sentence
     *   the domain problem {@link domain.Sentence}
     */
    public abstract void evaluate();

    /**
     * @param pastAssumptions 
     *   the collection of pastAssumptions to set
     */
    public void setPastAssumptions(ConcurrentLinkedQueue<Assumption> pastAssumptions) {
        this.pastAssumptions = pastAssumptions;
    }

    /**
     * @return the pastAssumptions
     */
    public ConcurrentLinkedQueue<Assumption> getPastAssumptions() {
        return pastAssumptions;
    }

    /**
     * Reset knowledge source clearing all past assumptions
     */
    public void reset() {
        pastAssumptions.clear();
        history.clear();
    }
       
    public abstract String toString();
    
}
