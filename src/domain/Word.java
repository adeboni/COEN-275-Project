/* src/domain/Word
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 21, 2016
 */

package domain;

import java.util.List;

public class Word extends BlackboardObject {

   protected List<CipherLetter> letters;
   protected Sentence sentence;
   protected Word prev, next;
   protected String word;

   /**
    * Default constructor
    */
    public Word() {
       this("", null);
    }

   /**
    * Constructor
    * @param w
    *          the word string
    * @param s
    *          the Sentence corresponding to this word
    */
   public Word(String w, Sentence s) {
      letters = null;
      sentence = s;
      prev = null;
      next = null;
      word = w;
   }
   
   /**
    * @return the current value of the world
    */
   public String value() {
      return word;
   }
   
   /**
    * @return true if this word is solved, else false
    */
   public boolean isSolved() {
      for (CipherLetter c: letters) {
         if (!c.isSolved()) {
            return false;
         }
      }
      return true;
   }
   
   /**
    * @return the sentence of the word
    */
   public Sentence sentence() {
      return sentence;
   }
   
   /**
    * @return the previous word of this word
    */
   public Word previous() {
      return prev;
   }
   
   /**
    * @return the next word of this word
    */
   public Word next() {
      return next;
   }

   /**
    * @return the letters
    */
   public List<CipherLetter> getLetters() {
      return letters;
   }

   /**
    * @param letters 
    *             the letters to set
    */
   public void setLetters(List<CipherLetter> letters) {
      this.letters = letters;
   }

   /**
    * @return the sentence
    */
   public Sentence getSentence() {
      return sentence;
   }

   /**
    * @param sentence 
    *             the sentence to set
    */
   public void setSentence(Sentence sentence) {
      this.sentence = sentence;
   }

   /**
    * @return the previous word of this word
    */
   public Word getPrevious() {
      return prev;
   }

   /**
    * @param prev 
    *          the previous word of this word to set
    */
   public void setPrevious(Word prev) {
      this.prev = prev;
   }

   /**
    * @return the next word of this word
    */
   public Word getNext() {
      return next;
   }

   /**
    * @param next 
    *          the next word of this word to set
    */
   public void setNext(Word next) {
      this.next = next;
   }

   /**
    * @return the word
    */
   public String getWord() {
      return word;
   }

   /**
    * @param word the word to set
    */
   public void setWord(String word) {
      this.word = word;
   }
}
