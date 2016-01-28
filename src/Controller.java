import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;

import domain.Assumption;
import knowledge.KnowledgeSource;
import knowledge.KnowledgeSources;
import knowledge.KnowledgeSourcesImpl;


public class Controller {

    /**
     * Attribute active or current knowledge source
     */
    private KnowledgeSource activeKnowledgeSource;

    /**
     * Attribute collection and entire problem domain of knowledge sources
     */
    private final KnowledgeSourcesImpl knowledgeSources = new KnowledgeSourcesImpl();

    /**
     * Attribute enum state of the controller
     */
    private ControllerState state;

    /**
     * Attribute blackboard or problem solving sandbox
     */
    private Blackboard blackboard;

    /**
     * Public constructor
     */
    public Controller() {
        /**
         * State - initializing
         */
        this.state = ControllerState.INITIALIZING;
    }

    /**
     * Public method to signal halt of the controller
     * 
     */
    public final void done() {
        System.exit(0); // Temporary for testing
        state = ControllerState.SOLVED;
    }

    /**
     * Public method to determine if the controller is stuck and cannot proceed
     * 
     * @return boolean primitive
     */
    public final boolean unableToProceed() {
        boolean result = false;

        if (state == ControllerState.STUCK) {
            result = true;
        }

        return result;
    }

    /**
     * Public method to determine if the controller is done (solved)
     * 
     * @return boolean primitive
     */
    public boolean isSolved() {

        boolean result = false;

        if (state == ControllerState.SOLVED) {
            result = true;
        }

        return result;
    }

    /**
     * Public method to cycle each KnowledgeSource and evaluate the current
     * blackboard problem string (repeatable method)
     */
    public final void processNextHint() {

        KnowledgeSourcesImpl knowledgeSources = (KnowledgeSourcesImpl) getKnowledgeSources();

        //Collections.sort(knowledgeSources);

        /**
         * go thru ks experts and choose the best one to go to the blackboard
         */
        for (KnowledgeSource ks : knowledgeSources) {

            ks.evaluate();
            state = ControllerState.EVALUATING;

            ConcurrentLinkedQueue<Assumption> queue = ks.getPastAssumptions();

            if (queue.size() > 0) {
                activeKnowledgeSource = ks;
                break;
            }

        }


        if (activeKnowledgeSource != null) {
            visitBlackboard(activeKnowledgeSource);
            leaveBlackboard(activeKnowledgeSource);
            activeKnowledgeSource = null;
        }

    }

    /**
     * Public reset method to null the brain and knowledge sources and create a
     * new brain. Knowledge sources are not created until the engage() method on
     * brain is called.
     */
    public final void reset() {

        activeKnowledgeSource = null;
        knowledgeSources.reset();
        state = ControllerState.INITIALIZING;

    }

    private void visitBlackboard(KnowledgeSource ks) {

        //blackboard = BlackboardContext.getInstance().getBlackboard();
        //blackboard.connect(ks);

    }


    private void leaveBlackboard(KnowledgeSource ks) {
        //blackboard.disconnect(ks);
    }

    public final void connect() {
        engage();
    }

    public void engage() {
    	knowledgeSources.init();
    }


    /**
     * @return the knowledgeSources
     */
    public KnowledgeSources getKnowledgeSources() {
        return knowledgeSources;
    }

}
