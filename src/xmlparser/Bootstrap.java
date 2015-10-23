package xmlparser;

import java.io.InputStream;
import java.util.Properties;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Bootstrap 
{
   //initialising stream for config file
   private static final InputStream is = Bootstrap.class.getResourceAsStream("../properties/config.properties");
   public static Properties properties = new Properties();
   
   //initializing logger
   public static final InputStream logis = Bootstrap.class.getResourceAsStream("../properties/log4j.properties");
   private static Logger logger = Logger.getLogger(XMLParser.getCurrentClassName());
   static{PropertyConfigurator.configure(logis);}
   
   public static void main(String[] args) 
   {
      try {
         //accosiate config file
         properties.load(is);
         
         //starting server
         Server server = new Server(); 
         ServerConnector connector = new ServerConnector(server);
         
         //setting port from config file
         connector.setPort(Integer.parseInt(properties.getProperty("http.port")));
             
         server.addConnector(connector);
         
         ServletContextHandler context = new ServletContextHandler();
         context.setContextPath("/");
         
         //adding servlet
         context.addServlet(XMLParserServlet.class, "/*");
         
         server.setHandler(context);
         
         server.start();
         server.join();
         
      } 
      catch (Exception e) 
      {
         logger.error("Error", e);
      }
   }
}
