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

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @version $Id$
 */
public class BugFreeBuggyActivationKeyDAO {

    public BugFreeBuggyActivationKeyDAO() {
    }

    @Test
    public void setValidationKeyValidityToFalse() {
        String userid = "USER_PART1";
        BuggyActivationKeyDAO dao = new BuggyActivationKeyDAO(new String[] {userid});
        
        boolean result,
                validity = false,
                expResult = true;

        result = dao.setValidationKeyValidity(userid, validity);
        assertEquals(expResult, result);
        assertEquals(validity, dao.isValidationKeyValid(userid));
    }

    @Test
    public void setValidationKeyValidityToTrue() {
        String userid = "USER_PART1";
        BuggyActivationKeyDAO dao = new BuggyActivationKeyDAO(new String[] {userid});
        boolean result,
                validity = true,
                expResult = true;

        result = dao.setValidationKeyValidity(userid, validity);
        assertEquals(expResult, result);
        assertEquals(validity, dao.isValidationKeyValid(userid));
    }

    @Test
    public void setValidationKeyValidityFails() {
        String userid = "USER_PART3";
        BuggyActivationKeyDAO dao = new BuggyActivationKeyDAO();
        boolean result,
                validity,
                expResult = false;

        // Initially it's false
        assertEquals(false, dao.isValidationKeyValid(userid));

        // Setting validity to true, should fail
        validity = true;
        result = dao.setValidationKeyValidity(userid, validity);
        assertEquals(expResult, result);
        // Same valitidy expectation
        assertEquals(false, dao.isValidationKeyValid(userid));

        // Setting validity to false, should fail
        validity = false;
        result = dao.setValidationKeyValidity(userid, validity);
        assertEquals(expResult, result);
        // Same valitidy expectation
        assertEquals(false, dao.isValidationKeyValid(userid));
    }
}
