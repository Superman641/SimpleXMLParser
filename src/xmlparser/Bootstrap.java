
package xmlparser;

import java.io.InputStream;
import java.util.Properties;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;


/**
 *
 * @author asus
 */
public class Bootstrap 
{
   public static void main(String[] args) 
   {
      
      try {
         InputStream is = Bootstrap.class.getResourceAsStream("../properties/config.properties");
         Properties properties = new Properties();
         properties.load(is);
        
         Server server = new Server(); 
         
         ServerConnector connector = new ServerConnector(server);
         connector.setPort(Integer.parseInt(properties.getProperty("http.port")));
         server.addConnector(connector);
        
         ServletContextHandler context = new ServletContextHandler();
         context.setContextPath("/");
         context.addServlet(XMLParserServlet.class, "/*");
         
         server.setHandler(context);
         
         server.start();
         server.join();
      } 
      catch (Exception e) 
      {
         throw new RuntimeException(e);
      }
   }
}
