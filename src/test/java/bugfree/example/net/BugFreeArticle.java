package bugfree.example.net;


import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.IOUtils;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ProvideSystemProperty;
import ste.xtest.net.StubURLConnection;
import ste.xtest.net.StubStreamHandler.URLMap;


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
        final URL TEST_URL1 = new URL("http://www.server1.com/1/index.html");
        final URL TEST_URL2 = new URL("http://www.server1.com/2/index.html");
        final URL TEST_URL3 = new URL("http://www.server1.com/3/index.html");
        
        StubURLConnection s1 = new StubURLConnection(TEST_URL1);
        StubURLConnection s2 = new StubURLConnection(TEST_URL2);
        StubURLConnection s3 = new StubURLConnection(TEST_URL3);
        
        URLMap.add(s1); URLMap.add(s2); URLMap.add(s3);
        
        s1.html("<html><body>hello world</html></body>");
        s2.file("src/test/data/abinaryfile.bin").type("application/myapp");
        s3.text("unauthorized").status(401);
        
        then(TEST_URL1.getContent()).isEqualTo("<html><body>hello world</html></body>");        
        
        URLConnection c2 = TEST_URL2.openConnection();
        then(c2.getContentType()).isEqualTo("application/myapp");
        then(IOUtils.toByteArray(c2.getInputStream()))
            .isEqualTo(IOUtils.toByteArray(new FileInputStream("src/test/data/abinaryfile.bin")));
        
        HttpURLConnection c3 = (HttpURLConnection)TEST_URL3.openConnection();
        then(c3.getResponseCode()).isEqualTo(401);
        
    }
}
