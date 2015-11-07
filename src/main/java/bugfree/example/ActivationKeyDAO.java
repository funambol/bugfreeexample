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

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ste
 */
public class ActivationKeyDAO {
    
    Map<String, Boolean> keys = new HashMap<>();
    
    /**
     * Sets the validation key for the given user to the given value.
     * 
     * @param username the user - NOT EMPTY
     * @param validity 
     * 
     * @throws IllegalArgumentException if any argument is not valid
     */
    public void setValidationKeyValidity(final String username, boolean validity) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username can not be blank");
        }
        keys.put(username, validity);
    }
    
    public boolean isValidationKeyValid(final String username) {
        usernameSanityCheck(username);
        if (!keys.containsKey(username)) {
            return false;
        }
        
        return keys.get(username);
    }
    
    private void usernameSanityCheck(final String username) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("username can not be blank");
        }
    }
}
