/* coen275proj.KS.LegalStringKnowledgeSource
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 23, 2016
 */
 
package knowledge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LegalStringKnowledgeSource extends StringKnowledgeSource {

   Set<String> dict;
   
   /* This function is the constructor.
    *     Input: none
    *    Output: none
    */
   public LegalStringKnowledgeSource() {
      reset();
   }

   /* This function resets the HashMap.
    *     Input: none
    *    Output: none
    */
   public void reset() {
      Scanner s;
      
      try {
         dict = new HashSet<String>();
         s = new Scanner(new File("resources/illegalStrings.txt"));
         while (s.hasNext()) {
            dict.add(s.next());
         }
         s.close();
      } catch (FileNotFoundException e) {
         System.out.println(e);
      }
   }
   
   /* This function evaluates the current blackboard and pick the double letter
    * with highest score.
    *     Input: none
    *    Output: none
    */
   public void evaluate() {
      // TODO: currently it only prints all of the illegal strings
      for (String s: dict) {
         System.out.println(s);
      }
   }
   
   public static void main(String[] args) {
      LegalStringKnowledgeSource ks = new LegalStringKnowledgeSource();
      ks.evaluate();
   }
}
