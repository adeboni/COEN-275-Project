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
   
   public Blackboard() {
      lst = new ArrayList<BlackboardObject>();
   }
   
   public void reset() {
      lst.clear();
   }
   
   public void assertProblem(String cipherStr) {
      // TODO: place initial problem onto the blackboard
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
   }
   
   public void connect() {
      // TODO: attach the knowledge source to the blackboard
   }
   
   public boolean isSolved() {
      // TODO
      return false;
   }
   
   public String retrieveSolution() {
      // TODO
      return "todo";
   }
   
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
   
   private List<CipherLetter> splitWord(Word w) {
      // TODO
      return null;
   }
}
