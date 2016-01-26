/* src/domain/Word
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 21, 2016
 */

package domain;

import java.util.List;
import java.util.Stack;

public class Word extends BlackboardObject {

   protected List<CipherLetter> letters;
   protected Sentence sentence;
   protected Word prev, next;
   protected String word;

   /* This function is the default constructor.
    *     Input: none
    *    Output: none
    */
    public Word() {
       this("", null);
    }

   /* This function is the constructor.
    *     Input: word string
    *    Output: none
    */
   public Word(String w, Sentence s) {
      letters = null;
      sentence = s;
      prev = null;
      next = null;
      word = w;
   }
   
   /* This function returns the current value of the word.
    *     Input: none
    *    Output: the current value of the word
    */
   public String value() {
      return word;
   }
   
   /* This function return true if there is an assertion for every letter in the 
    * word.
    *     Input: none
    *    Output: true if word is solved, else false
    */
   public boolean isSolved() {
      for (CipherLetter c: letters) {
         if (!c.isSolved()) {
            return false;
         }
      }
      return true;
   }
   
   /* This function returns the sentence of the word.
    *     Input: none
    *    Output: sentence of the word
    */
   public Sentence sentence() {
      return sentence;
   }
   
   /* This function returns the previous word of this word.
    *     Input: none
    *    Output: the previous word of this word
    */
   public Word previous() {
      return prev;
   }
   
   /* This function returns the next word of this word.
    *     Input: none
    *    Output: the next word of this word
    */
   public Word next() {
      return next;
   }
   
   /* This function returns the list of cipher letters.
    *     Input: none
    *    Output: word
    */
   public List<CipherLetter> getLetters() {
      return letters;
   }
   
   /* This function returns the sentence of the word.
    *     Input: none
    *    Output: sentence
    */
   public Sentence getSentence() {
      return sentence;
   }
   
   /* This function returns the previous word of this word.
    *     Input: none
    *    Output: previous word
    */
   public Word getPrevious() {
      return prev;
   }
   
   /* This function returns the next word of this word.
    *     Input: none
    *    Output: next word
    */
   public Word getNext() {
      return next;
   }
   
   /* This function returns the word.
    *     Input: none
    *    Output: word
    */
   public String getWord() {
      return word;
   }
   
   /* This function sets the sentence of the word.
    *     Input: sentence
    *    Output: none
    */
   public void setSentence(Sentence s) {
      sentence = s;
   }
   
   /* This function sets the previous word of this word.
    *     Input: previous word
    *    Output: none
    */
   public void setPrevious(Word w) {
      prev = w;
   }
   
   /* This function sets the next word of this word.
    *     Input: next word
    *    Output: none
    */
   public void setNext(Word w) {
      next = w;
   }
   
   /* This function sets the word.
    *     Input: previous word
    *    Output: none
    */
   public void setWord(String str) {
      word = str;
   }
   
   /* This function sets the list of cipher letters.
    *     Input: list of cipher letters
    *    Output: none
    */
   public void setLetters(List<CipherLetter> lst) {
      letters = lst;
   }
}
