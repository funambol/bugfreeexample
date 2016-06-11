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

import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author ste
 */
public class BuggyActivationKeyDAO {
    final HashMap<String, Boolean> USERS = new HashMap<>();
    
    public BuggyActivationKeyDAO(String... users) {
        for (String u: users) {
            USERS.put(u, Boolean.FALSE);
        }
    }
    
    public boolean setValidationKeyValidity(String user, boolean validity) {
        if (USERS.containsKey(user)) {
            USERS.put(user, validity);
            
            return true;
        }
        
        return false;
    }
    
    public boolean isValidationKeyValid(String user) {
        Boolean ret = USERS.get(user);
        
        return (ret != null) ? ret : false;
    }
}
