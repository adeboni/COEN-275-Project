package blackboard;

public final class BlackboardContext {

    /**
     * Attribute singleton instance
     */
    private static BlackboardContext instance;
    
    /**
     * Attribute blackboard
     */
    private Blackboard blackboard;

    /**
     * Attribute controller
     */
    private Controller controller;

    
    /**
     * Hidden constructor
     */
    private BlackboardContext() {
        this.blackboard = new Blackboard();
        this.controller = new Controller();
    }
    
    /**
     * Method to return singleton instance
     * @return {@link BlackboardContext}
     */
    public static BlackboardContext getInstance() {
        if (instance == null) {
            instance = new BlackboardContext();
        }
        return instance;
    }

    /**
     * @return the {@link Blackboard} object
     */
    public final Blackboard getBlackboard() {
        return blackboard;
    }

    /**
     * @return the {@link Controller} object
     */
    public final Controller getController() {
        return controller;
    }
}
