package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import knowledge.KnowledgeSource;


public abstract class Dependent {

    /**
     * Attribute collection of participating experts, thinkers, or general
     * consults.
     */
    protected List<KnowledgeSource> references = new ArrayList<KnowledgeSource>();

    /**
     * Public method to add a new knowledge source reference
     * 
     * @param ref
     *    the {@link knowledge.KnowledgeSource} reference
     * @return true if success
     */
    public boolean addReference(KnowledgeSource ref) {
        return references.add(ref);
    }

    /**
     * Public method to return the number of knowledge source references
     * 
     * @return integer number of references
     */
    public int numberOfReferences() {
        return references.size();
    }

    /**
     * Public method to remove a knowledge source reference
     * 
     * @param ref
     *   the {@link knowledge.KnowledgeSource} reference
     * @return true if success
     */
    public boolean removeReference(KnowledgeSource ref) {
        return references.remove(ref);
    }

    public enum Direction {
    	FORWARD, REVERSE
    }
    
    public void notify(Direction direction, Assumption statement) {
        ConcurrentLinkedQueue<Assumption> queue;
        Iterator<Assumption> iter;
        Assumption stmt;
        
        /**
         * Forward chaining Knowledge Sources
         */
        if (direction == Direction.FORWARD) {
            for (KnowledgeSource knowledgeSource : references) {
                knowledgeSource.getPastAssumptions().add(statement);
            }
        }
        
        /**
         * Reverse chaining Knowledge Sources
         */
        if (direction == Direction.REVERSE) {
            for (KnowledgeSource knowledgeSource : references) {
                queue = knowledgeSource.getPastAssumptions();
                iter = queue.iterator();
                while (iter.hasNext()) {
                    stmt = (Assumption) iter.next();
                    if (stmt.equals(statement)) {
                        iter.remove();
                    }
                }
            }
        } 
    }

}
