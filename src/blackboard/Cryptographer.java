package blackboard;

public final class Cryptographer {

    /**
     * Attribute blackboard where problem is solved
     */
    private Blackboard blackboard;

    /**
     * Attribute controller for problem solving logic
     */
    private Controller controller;

    /**
     * Public method to decipher the coded cipher text
     * 
     * @param ciphertext
     *            the String to be decoded or translated into a meaningful
     *            sentence
     * @return String decrypted
     */
    public String decipher(String ciphertext) {
        init();
        blackboard.reset();
        blackboard.assertProblem(ciphertext);
        controller.reset();
        controller.connect();
        return runController();
    }

    /**
     * Initialize the cryptographer and get the context objects
     */
    private void init() {
        BlackboardContext context = BlackboardContext.getInstance();
        this.blackboard = context.getBlackboard();
        this.controller = context.getController();
    }

    /**
     * Private method to loop while the controller processes hints to solve the
     * puzzle.
     * 
     * @return solution as String
     */
    private String runController() {
        int maxTries = 3;
        for (int i = 0; i < maxTries; ++i) {
            controller.processNextHint();
            if (blackboard.isSolved()) {
                controller.done();
                return blackboard.retrieveSolution().value();
            }
        }
        
        return "I could not decipher the asserted problem!";
    }
}
