/* src/Blackboard
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 23, 2016
 */

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import domain.BlackboardObject;
import domain.CipherLetter;
import domain.Sentence;
import domain.Word;

public class Blackboard {
   
   List<BlackboardObject> lst;
   
   /**
    * Default constructor
    */

   public Blackboard() {
      lst = new ArrayList<BlackboardObject>();
   }
   
   /**
    * This function resets the blackboard.
    */
   public void reset() {
      lst.clear();
   }
   
   /**
    * This function asserts the problem onto the blackboard.
    *     Input: sentence string
    *    Output: none
    */
   public void assertProblem(String cipherStr) {
      Sentence s;
      List<Word> words;
      List<CipherLetter> letters;
      
      // trim the input sentence
      cipherStr = cipherStr.trim();
      if (cipherStr.equals("")) {
         return;
      }
      s = new Sentence(cipherStr);
      
      // split sentence into words
      words = splitSentence(s);
      s.setWords(words);
      
      // split words into letters
      for (Word w: words) {
         letters = splitWord(w);
         w.setLetters(letters);
      }
      
      // register everything onto blackboard
      registerObjects(s);
   }
   
   public void connect() {
      // TODO: attach the knowledge source to the blackboard
   }
   
   /**
    * This function returns whether the problem is solved or not.
    *     Input: none
    *    Output: true if problem is solved, else false
    */
   public boolean isSolved() {
      for (BlackboardObject obj: lst) {
         if (obj.getClass().equals(Sentence.class)) {
            return ((Sentence) obj).isSolved();
         }
      }
      
      return false;
   }
   
   /**
    * This function retrieves the solution from the blackboard if it is solved.
    *     Input: none
    *    Output: the solution sentence
    */
   public Sentence retrieveSolution() {
      Sentence s;
      
      for (BlackboardObject obj: lst) {
         if (obj.getClass().equals(Sentence.class)) {
            s = (Sentence) obj;
            if (s.isSolved()) {
               return s;
            }
         }
      }
      
      return null; 
   }
   
   /**
    * This function adds the object onto the blackboard.
    *     Input: object
    *    Output: none
    */
   public void add(BlackboardObject obj) {
      lst.add(obj);
   }
   
   /**
    * This function removes the object from the blackboard.
    *     Input: object
    *    Output: none
    */
   public void remove(BlackboardObject obj) {
      lst.remove(obj);
   }
   
   /**
    * This function splits the sentence into a list of words.
    *     Input: sentence
    *    Output: list of words
    */
   private List<Word> splitSentence(Sentence s) {
      StringTokenizer toker;
      List<Word> words;
      Word prev, curr;
      
      words = new ArrayList<Word>();
      prev = null;
      toker = new StringTokenizer(s.value());
      while (toker.hasMoreTokens()) {
         curr = new Word(toker.nextToken(), s);
         curr.setPrevious(prev);
         if (prev != null) {
            prev.setNext(curr);
         }
         words.add(curr);
         prev = curr;
      }
      
      return words;
   }
   
   /**
    * This function splits the word into a list of cipher letters.
    *     Input: word
    *    Output: list of cipher letters
    */
   private List<CipherLetter> splitWord(Word w) {
      List<CipherLetter> letters;
      CipherLetter c;
      String str;

      str = w.value();
      letters = new ArrayList<CipherLetter>();
      for (int i = 0; i < str.length(); ++i) {
         c = new CipherLetter(str.charAt(i));
         letters.add(c);
      }
      
      return letters;
   }
   
   /**
    * This function registers the objects generated from the problem to the
    * blackboard.
    *     Input: sentence
    *    Output: none
    */
   private void registerObjects(Sentence s) {
      List<Word> words;
      List<CipherLetter> letters;
      
      s.register();
      
      words = s.getWords();
      for (Word w: words) {
         w.register();
         
         letters = w.getLetters();
         for (CipherLetter c: letters) {
            c.register();
         }
      }
   }
}
