/* src/domain/Assumption
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 25, 2016
 */

package domain;

import knowledge.KnowledgeSource;

public class Assumption extends BlackboardObject {
   private BlackboardObject target;
   private KnowledgeSource creator;
   private String reason;
   private char plainLetter;
   private char cipherLetter;
   
   /**
    * Default constructor
    */
   public Assumption() {
      target = null;
      creator = null;
   }
   
   /**
    * @return true
    *             as Assumption is always retractable
    */
   public boolean isRetractable() {
      return true;
   }

   /**
    * @return the target
    */
   public BlackboardObject getTarget() {
      return target;
   }

   /**
    * @param target the target to set
    */
   public void setTarget(BlackboardObject target) {
      this.target = target;
   }

   /**
    * @return the creator
    */
   public KnowledgeSource getCreator() {
      return creator;
   }

   /**
    * @param creator the creator to set
    */
   public void setCreator(KnowledgeSource creator) {
      this.creator = creator;
   }

   /**
    * @return the reason
    */
   public String getReason() {
      return reason;
   }

   /**
    * @param reason the reason to set
    */
   public void setReason(String reason) {
      this.reason = reason;
   }

   /**
    * @return the plainLetter
    */
   public char getPlainLetter() {
      return plainLetter;
   }

   /**
    * @param plainLetter the plainLetter to set
    */
   public void setPlainLetter(char plainLetter) {
      this.plainLetter = plainLetter;
   }

   /**
    * @return the cipherLetter
    */
   public char getCipherLetter() {
      return cipherLetter;
   }

   /**
    * @param cipherLetter the cipherLetter to set
    */
   public void setCipherLetter(char cipherLetter) {
      this.cipherLetter = cipherLetter;
   }
}
