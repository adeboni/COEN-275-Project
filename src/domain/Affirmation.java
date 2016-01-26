/* src/domain/Affirmation
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 25, 2016
 */

package domain;

import java.util.Stack;

public class Affirmation {

   private Stack<Assumption> statements;
   
   /**
    * Default constructor
    */
   public Affirmation() {
      statements = new Stack<Assumption>();
   }
   
   /**
    * Make a statement.
    * @param a
    *          the Assumption to put onto the top of the stack
    */
   public void make(Assumption a) {
      statements.push(a);
   }
   
   /**
    * Retract a statement.
    * @return the Assumption at the top of the stack with removal
    */
   public Assumption retract() {
      // TODO: avoid Assertions being retract?
      return statements.pop();
   }
   
   /**
    * @return the ciphertext of the statement of the top of the stack
    */
   public char ciphertext() {
      return mostRecent().getCipherLetter();
   }
   
   /**
    * @return the plaintext of the statement of the top of the stack
    */
   public char plaintext() {
      return mostRecent().getPlainLetter();
   }
   
   /**
    * @return true if there is asserted statement in the stack
    */
   public boolean isPlainLetterAsserted() {
      for (int i = statements.size(); i > 0; --i) {
         if (!statementAt(i).isRetractable()) {
            return true;
         }
      }
      return false;
   }
   
   /**
    * @return true if there is asserted statement in the stack
    */
   public boolean isCipherLetterAsserted() {
      for (int i = statements.size(); i > 0; --i) {
         if (!statementAt(i).isRetractable()) {
            return true;
         }
      }
      return false;
   }
   
   /**
    * @return true if there are statements in the stack
    */
   public boolean plainLetterHasAssumption() {
      return statements.size() > 0;
   }
   
   /**
    * @return true if there are statements in the stack
    */
   public boolean cipherLetterHasAssumption() {
      return statements.size() > 0;
   }
   
   /**
    * @return the statement at the top of the stack without removing
    */
   public Assumption mostRecent() {
      return statements.peek();
   }
   
   /**
    * @param i
    *          the index of the statement
    * @return the statement at index i
    */
   public Assumption statementAt(int i) {
      return statements.get(i);
   }
   
   /**
    * @return the stack of statements
    */
   public Stack<Assumption> getStatements() {
      return statements;
   }
   
   /**
    * @param statements
    *                the stack of statements
    */
   public void setStatements(Stack<Assumption> statements) {
      this.statements = statements;
   }
}
