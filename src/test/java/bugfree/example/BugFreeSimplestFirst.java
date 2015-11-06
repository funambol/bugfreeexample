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
    
    @Test
    public void setValidationKeyValid_sets_validation_key_by_user() {
        ActivationKeyDAO dao = new ActivationKeyDAO();
                
        //
        // set validation key for user 1
        //
        for (boolean value: new boolean[true, false, false, true]) {
            dao.setValidationKeyValidity("user1", value);
            then(dao.isValidationKeyValid("user1")).isEqualTo(value);
        }
        
        //
        // so far the implementation must be enough to satisfy the first simple
        // requirement above; if can return always true. What about another
        // user?
        //
        
        //
        // set validation key for user 2
        //
        dao.setValidationKeyValidity("user2", true);
        then(dao.isValidationKeyValid("user2")).isTrue();
    }
    
    @Test
    public void isValidationKeyValid_returns_true_if_validation_key_is_valid() {
        
    }
}
