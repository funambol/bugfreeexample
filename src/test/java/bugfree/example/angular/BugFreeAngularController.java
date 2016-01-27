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
package bugfree.example.angular;

import java.io.File;
import java.io.IOException;
import javax.script.ScriptException;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.Ignore;
import org.junit.Test;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeObject;
import ste.xtest.js.BugFreeEnvjs;
import ste.xtest.js.JSAssertions;
import ste.xtest.net.StubURLBuilder;

/**
 *
 * @author ste
 */
public class BugFreeAngularController extends BugFreeEnvjs {
    public BugFreeAngularController() throws ScriptException, IOException {
        loadScript("/js/ecma5-adapter.js");
        loadScript("/js/angular-rhino.js");
        loadScript("/js/envjs.urlstubber.js");
        loadScript("src/main/webapp/angular/js/controllers.js");
        loadScript("src/test/angular/fake.js");
    }
    
    @Test
    public void load_controller_and_data_from_url() throws Exception {
        givenURLStubs("phones1.json");
 
        exec("controller('PhoneListCtrl', {$scope: scope});");
        
        JSAssertions.then((NativeArray)exec("scope.phones;")).hasSize(3);
        thenPhoneIs(0, "Nexus S", "Fast just got faster with Nexus S.");
        thenPhoneIs(2, "MOTOROLA XOOM™", "The Next, Next Generation tablet.");
    }
    
    @Test
    public void content_is_dynamic() throws Exception {
        givenURLStubs("phones2.json");
 
        exec("controller('PhoneListCtrl', {$scope: scope});");
        
        JSAssertions.then((NativeArray)exec("scope.phones;")).hasSize(1);
        thenPhoneIs(0, "Huawei", "Best price/quality ratio");
    }
    
    @Test
    public void set_status_in_case_of_errors() throws Exception {
        //
        // data error
        //
        givenURLStubsWithError(404); // not found
        
        exec("Envjs.DEBUG = true; controller('PhoneListCtrl', {$scope: scope});");
        
        JSAssertions.then((NativeArray)exec("scope.phones;")).isEmpty();
        then(exec("scope.status;")).isEqualTo("error");
        
        //
        // in case of success status becomes "ok"
        //
        givenURLStubs("phones2.json"); 
        
        exec("controller('PhoneListCtrl', {$scope: scope});");
        
        then(exec("scope.status;")).isEqualTo("ok");
        
        //
        // server error
        //
        givenURLStubsWithError(500); 
        
        exec("controller('PhoneListCtrl', {$scope: scope});");
        
        JSAssertions.then((NativeArray)exec("scope.phones;")).isEmpty();
        then(exec("scope.status;")).isEqualTo("error");
    }
    
    // --------------------------------------------------------- private methods
    
    private void givenURLStubs(final String file) throws Exception {
        StubURLBuilder[] builders = prepareUrlStupBuilders(
            "file://" + new File("phones/phones.json").getAbsolutePath()
        );
        builders[0].file("src/test/angular/" + file).type("application/json");
    }
    
    private void givenURLStubsWithError(int status) throws Exception {
        StubURLBuilder[] builders = prepareUrlStupBuilders(
            "file://" + new File("phones/phones.json").getAbsolutePath()
        );
        builders[0].text("pippo").status(status);
    }
    
    private void thenPhoneIs(int index, final String phone, final String snippet)
    throws Exception {
        NativeObject p = (NativeObject)exec("scope.phones[" + index + "];");
        then(p.get("name", null)).isEqualTo(phone);
        then(p.get("snippet", null)).isEqualTo(snippet);
    }
    
}
