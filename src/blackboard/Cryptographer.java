package blackboard;

import java.util.List;

import domain.CipherLetter;
import util.BlackboardUtil;

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
     *            the String to be decoded or translated into a meaningful sentence
     * @return String decrypted
     */
    public String decipher(String ciphertext) {
    	BlackboardContext context = BlackboardContext.getInstance();
        blackboard = context.getBlackboard();
        controller = context.getController();
        blackboard.reset();
        blackboard.assertProblem(ciphertext);
        controller.reset();
        controller.connect();
        blackboard.initHistory();
        BlackboardUtil.initSentenceState(blackboard.getSentence());
        return runController();
    }

    /**
     * Private method to loop while the controller processes hints to solve the puzzle.
     * 
     * @return solution as String
     */
    private String runController() {
        int maxTries = 20;
        for (int i = 0; i < maxTries; ++i) {
            controller.processNextHint();
            if (blackboard.isSolved()) {
                controller.done();
                
                List<List<CipherLetter>> sentence = BlackboardUtil.getCurrentSentenceState();
                String ret = "";
                
                for (List<CipherLetter> word : sentence) {
                	for (CipherLetter letter : word) {
                		ret += letter.getAffirmations().getSolvedLetter().getPlainLetter();
                	}
                	ret += " ";
                }
                
                return ret;
            }
        }
        
        return "I could not decipher the asserted problem!";
    }
}
