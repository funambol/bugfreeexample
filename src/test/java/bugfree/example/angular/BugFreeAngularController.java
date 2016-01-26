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
    public void load_controller_and_data() throws Exception {
        givenURLStubs("phones1.json");
 
        exec("controller('PhoneListCtrl', {$scope: scope});");
        
        JSAssertions.then((NativeArray)exec("scope.phones;")).hasSize(3);
        NativeObject phone = (NativeObject)exec("scope.phones[0];");
        then(phone.get("name", null)).isEqualTo("Nexus S");
        then(phone.get("snippet", null)).isEqualTo("Fast just got faster with Nexus S.");
        phone = (NativeObject)exec("scope.phones[2];");
        then(phone.get("name", null)).isEqualTo("MOTOROLA XOOM™");
        then(phone.get("snippet", null)).isEqualTo("The Next, Next Generation tablet.");
    }
    
    // --------------------------------------------------------- private methods
    
    private void givenURLStubs(final String file) throws Exception {
        StubURLBuilder[] builders = prepareUrlStupBuilders(
            "file://" + new File("phones/phones.json").getAbsolutePath()
        );
        System.out.println(">> " + "file://" + new File("phones/phones.json").getAbsolutePath());
        
        builders[0].file("src/test/angular/" + file).type("application/json");
    }
    
}
