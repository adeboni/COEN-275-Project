package blackboard;

import knowledge.KnowledgeSource;
import knowledge.KnowledgeSourcesImpl;

public class Controller {

    private enum ControllerState {
        INITIALIZING, STUCK, EVALUATING, SOLVED
    }

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
        this.state = ControllerState.INITIALIZING;
    }

    /**
     * Public method to signal halt of the controller
     * 
     */
    public final void done() {
        state = ControllerState.SOLVED;
    }

    /**
     * Public method to determine if the controller is stuck and cannot proceed
     * 
     * @return boolean primitive
     */
    public final boolean unableToProceed() {
        return state == ControllerState.STUCK;
    }

    /**
     * Public method to determine if the controller is done (solved)
     * 
     * @return boolean primitive
     */
    public boolean isSolved() {
        return state == ControllerState.SOLVED;
    }

    /**
     * Public method to cycle each KnowledgeSource and evaluate the current
     * blackboard problem string (repeatable method)
     */
    public final void processNextHint() {
        for (KnowledgeSource ks : knowledgeSources) {
            ks.evaluate();
            state = ControllerState.EVALUATING;

            if (ks.getPastAssumptions().size() > 0) {
                activeKnowledgeSource = ks;
                break;
            }
        }

        if (activeKnowledgeSource == null) {
            System.err.println("Not enough implemented knowledge sources!");
            System.exit(0);
        }
        
        System.out.println("processNextHint-> The " + activeKnowledgeSource.toString() + " is now active.");

        visitBlackboard(activeKnowledgeSource);
        leaveBlackboard(activeKnowledgeSource);
        activeKnowledgeSource = null;
    }


    public final void reset() {
        activeKnowledgeSource = null;
        knowledgeSources.reset();
        state = ControllerState.INITIALIZING;
    }

    /**
     * Private method for KnowledgeSource expert to have a turn at the
     * blackboard problem
     * 
     * @param the KnowledgeSource
     */
    private void visitBlackboard(KnowledgeSource ks) {
        blackboard = BlackboardContext.getInstance().getBlackboard();
        blackboard.connect(ks);
    }

    /**
     * Private method to leave or disengage from the blackboard.
     * 
     * @param the KnowledgeSource
     */
    private void leaveBlackboard(KnowledgeSource ks) {
        blackboard.disconnect(ks);
    }

    /**
     * Public method to engage and load knowledge sources
     */
    public final void connect() {
        // knowledgeSources.init();
    }
    
    /**
     * @return the knowledgeSources
     */
    public KnowledgeSourcesImpl getKnowledgeSources() {
        return knowledgeSources;
    }

}
