/* src/domain/CipherLetter
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 25, 2016
 */

package domain;

public class CipherLetter extends BlackboardObject {

   protected char letter;
   protected Affirmation affirmations;

   /**
    * Default constructor
    */
   public CipherLetter() {
      affirmations = new Affirmation();
   }
   
   /**
    * Constructor
    * @param c
    *          cipher letter
    */
   public CipherLetter(char c) {
      this();
      this.letter = c;
   }
   
   /**
    * This function returns the current value of the letter.
    *     Input: none
    *    Output: the current value of the sentence
    */
   public char value() {
      return letter;
   }
   
   /**
    * This function return true if there is an assertion for the letter.
    *     Input: none
    *    Output: true if the letter is solved, else false
    */
   public boolean isSolved() {
      // look for the latest assertion statement (near the top of the stack, 
      // the end of the vector) from affirmations
      for (int i = affirmations.getStatements().size(); i > 0; --i) {
         if (!affirmations.statementAt(i).isRetractable()) {
            return true;
         }
      }
      return false;
   }

   /**
    * @return the affirmations
    */
   public Affirmation getAffirmations() {
      return affirmations;
   }

   /**
    * @param affirmations
    *                the affirmations to set
    */
   public void setAffirmations(Affirmation affirmations) {
      this.affirmations = affirmations;
   }
   
   /**
    * @return the letter
    */
   public char getLetter() {
      return letter;
   }

   /**
    * @param letter
    *             the letter to set
    */
   public void setLetter(char letter) {
      this.letter = letter;
   }
}
