/* src/domain/CipherLetter
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 21, 2016
 */

package domain;

public class CipherLetter extends BlackboardObject {

   protected char letter;
   protected Affirmation affirmations;

   // constructors
   public CipherLetter() {
      affirmations = new Affirmation();
   }
   
   public CipherLetter(char c) {
      this();
      this.letter = c;
   }
   
   /* This function returns the current value of the letter.
    *     Input: none
    *    Output: the current value of the sentence
    */
   public char value() {
      return letter;
   }
   
   /* This function return true if there is an assertion for the letter.
    *     Input: none
    *    Output: true if the letter is solved, else false
    */
   public boolean isSolved() {
      // TODO
      return false;
   }
}
