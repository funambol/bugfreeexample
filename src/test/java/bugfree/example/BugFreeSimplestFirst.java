/*
 * <FUNAMBOLCOPYRIGHT>
 * Copyright (C) 2015 Funambol.
 * All Rights Reserved.  No use, copying or distribution of this
 * work may be made except in accordance with a valid license
 * agreement from Funambol.  This notice must be
 * included on all copies, modifications and derivatives of this
 * work.
 *
 * Funambol MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. Funambol SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * </FUNAMBOLCOPYRIGHT>
 */
package bugfree.example;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.Test;

/**
 * This is an example whose goal is to show that writing the simplest code first
 * and them add code only driven by additional requirements helps avoid trivial 
 * but dangerous bugs.
 * 
 * @author ste
 */
public class BugFreeSimplestFirst {
    
    private final String[] USERS = new String[] {"user-one", "user-two", "user-three"};
    
    @Test
    public void setValidationKeyValid_sets_validation_key_by_user() {
        BuggyActivationKeyDAO dao = new BuggyActivationKeyDAO(USERS);
                
        //
        // set validation key for a user
        //
        for (String username: USERS) {
            for (boolean value: new boolean[] {true, false, false, true}) {
                dao.setValidationKeyValidity(username, value);
                then(dao.isValidationKeyValid(username)).isEqualTo(value);
            }
        }
    }
    
    @Test
    public void validation_key_is_invalid_if_the_user_does_not_exist() {
        BuggyActivationKeyDAO dao = new BuggyActivationKeyDAO(USERS);
        
        //
        // no validations so far...
        //
        for (String username: USERS) {
            then(dao.isValidationKeyValid(username)).isFalse();
        }
        
        //
        // adding some validation
        //
        dao.setValidationKeyValidity("anotheruser1", true);
        dao.setValidationKeyValidity("anotheruser2", false);
        
        for (String username: USERS) {
            then(dao.isValidationKeyValid(username)).isFalse();
        }
    }
}
