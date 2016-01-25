/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bugfree.example;

/**
 *
 * @author ste
 */
public class BuggyActivationKeyDAO {
    boolean validity = false;
    final String USER = "USER_PART1";
    
    public boolean setValidationKeyValidity(String user, boolean validity) {
        if (USER.equals(user)) {
            this.validity = validity;
            
            return true;
        }
        
        return false;
    }
    
    public boolean isValidationKeyValid(String user) {
        return validity;
    }
}
