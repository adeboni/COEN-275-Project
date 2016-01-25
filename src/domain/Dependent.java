/* src/domain/Dependent
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 21, 2016
 */

package domain;

import java.util.ArrayList;
import java.util.List;

import knowledge.KnowledgeSource;

public abstract class Dependent {

   List<KnowledgeSource> references = new ArrayList<KnowledgeSource>();

   /* This function adds the KnowledgeSource ks into the list of references.
    *     Input: Knowledge Source 
    *    Output: none
    */
   public void add(KnowledgeSource ks) {
      references.add(ks);
   }
   
   /* This function removes the KnowledgeSource ks from the list of references.
    *     Input: Knowledge Source 
    *    Output: none
    */
   public void remove(KnowledgeSource ks) {
      references.remove(ks);
   }
   
   /* This function returns the size of the list of references.
    *     Input: none
    *    Output: size of the list of references
    */
   public int numberOfDependents() {
      return references.size();
   }
   
   /* This function broadcast an operation of each dependent.
    *     Input: dependentId, operation
    *    Output: none
    */
   public void notify(String dependentId, String opeartion) {
      // TODO
      // 1) Forward Chaining
      // 2) Reverse Chaining
   }
}
