/* src/domain/Affirmation
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 23, 2016
 */

package domain;

import java.util.Stack;

public class Affirmation {

   private Assumption assumptionStatement;
   private Assertion assertionStatement;
   protected Stack<Assumption> statements;
   
   public Affirmation() {
      assumptionStatement = null;
      assertionStatement = null;
      statements = new Stack<Assumption>();
   }
   
   public void makeAssumption(Assumption a) {
      assumptionStatement = a;
      statements.push(a);
   }
   
   public void makeAssertion(Assertion a) {
      assertionStatement = a;
   }
   
   public Assumption retractAssumption() {
      assumptionStatement = null;
      return statements.pop();
   }
   
   public Assertion retractAssertion() {
      Assertion a;
      a = assertionStatement;
      assertionStatement = null;
      return a;
   }
   
   public char ciphertext() {
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
}
