package domain;

import knowledge.KnowledgeSource;


public class Assumption extends BlackboardObject {

    protected KnowledgeSource creator;
    
    protected BlackboardObject target;
       
    protected String reason;
    
    protected String cipherLetter;
    
    protected String plainLetter;
    
    protected boolean validFlag = true;
    
    /**
     * Assumption is always retractable. Please note that an Assertion (extends)
     * is not retractable and therefore overrides this method and returns false
     * 
     * @return true (always)
     */
    public boolean isRetractable() {
        return true;
    }

    /**
     * @return the cipherLetter
     */
    public String getCipherLetter() {
        return cipherLetter;
    }

    /**
     * @param cipherLetter the cipherLetter to set
     */
    public void setCipherLetter(String cipherLetter) {
        this.cipherLetter = cipherLetter;
    }

    /**
     * @return the plainLetter
     */
    public String getPlainLetter() {
        return plainLetter;
    }

    /**
     * @param plainLetter the plainLetter to set
     */
    public void setPlainLetter(String plainLetter) {
        this.plainLetter = plainLetter;
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
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

   /**
    * @return the removeFlag
    */
   public boolean isValid() {
      return validFlag;
   }

   /**
    * @param removeFlag the removeFlag to set
    */
   public void setValidFlag(boolean validFlag) {
      this.validFlag = validFlag;
   }

}
