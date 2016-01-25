/* src/domain/Assertion
 * ------------------------------
 * Written by: Sui Fung Alex Wong
 *       Date: Jan 25, 2016
 */

package domain;

public class Assertion extends Assumption {
   /**
    * @return false
    *             as Assertion is not retractable
    */
   @Override
   public boolean isRetractable() {
      return false;
   }
}
