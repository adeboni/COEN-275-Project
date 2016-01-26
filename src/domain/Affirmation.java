/* src/domain/Affirmation
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 25, 2016
 */

package domain;

import java.util.Stack;

public class Affirmation {

   private Stack<Assumption> statements;
   
   public Affirmation() {
      statements = new Stack<Assumption>();
   }
   
   public void make(Assumption a) {
      statements.push(a);
   }
   
   public Assumption retract() {
      return statements.pop();
   }
   
   char ciphertext() {
      // TODO
      return 'A';
   }
   
   public char plaintext() {
      // TODO
      return 'A';
   }
   
   public boolean isPlainLetterAsserted() {
      // TODO
      return false;
   }
   
   public boolean isCipherLeterAsserted() {
      // TODO
      return false;
   }
   
   public boolean plainLetterHasAssumption() {
      // TODO
      return false;
   }
   
   public boolean cipherLetterHasAssumption() {
      // TODO
      return false;
   }
   
   public Assumption mostRecent() {
      return statements.peek();
   }
   
   public Assumption statementAt(int i) {
      return statements.get(i);
   }
   
   public Stack<Assumption> getStatements() {
      return statements;
   }
   
   public void setStatements(Stack<Assumption> statements) {
      this.statements = statements;
   }
}
