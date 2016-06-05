package bugfree.example.net;


import java.net.URL;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ProvideSystemProperty;
import ste.xtest.net.StubStreamHandler;


/**
 *
 * @author ste
 */
public class BugFreeArticle {
    
    @Rule
    public final ProvideSystemProperty PACKAGE_HANDLERS
	 = new ProvideSystemProperty("java.protocol.handler.pkgs", "ste.xtest.net");
    
    @Test
    /**
     * see https://docs.oracle.com/javase/7/docs/api/java/net/URL.html#URL%28java.lang.String,%20java.lang.String,%20int,%20java.lang.String%29
     */
    public void retrieve_simple_url_content() throws Exception {
        
        StubStreamHandler h1 = StubStreamHandler.add("http://www.server1.com/index.html");
        h1.html("<html><body>hello world</html></body>");
        
        URL u1 = new URL("http://www.server1.com/index.html");
        
        then(u1.getContent()).isEqualTo("<html><body>hello world</html></body>");
        
    }
    /*
        StubStreamHandler h2 = new StubStreamHandler("http://www.server1.com/index.html");
        StubStreamHandler h3 = new StubStreamHandler("http://www.server1.com/index.html")

        h1.html("<html><body>hello world</html></body>");
        h2.file("/tmp/abinaryfile.bin").type("application/myapp");
        h3.text("unauthorized").status(401);
        
        URL u1 = h1.build();
        
    }*/
}
