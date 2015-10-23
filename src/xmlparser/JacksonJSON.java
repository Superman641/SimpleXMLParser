package xmlparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class JacksonJSON
{
   //initializing logger
   public static final InputStream logis = Bootstrap.class.getResourceAsStream("../properties/log4j.properties");
   private static Logger logger = Logger.getLogger(XMLParser.getCurrentClassName());
   static{PropertyConfigurator.configure(logis);}
   
   public static String makeJSON(Envelope envelope)
   {
      //initialize writer and maaper
      Writer writer = new StringWriter();
      ObjectMapper mapper = new ObjectMapper();
      
      try 
      {  
         //converting input Envelope object ot JSON
         mapper.writeValue(writer, envelope);
      } 
      catch (IOException ioe) 
      {
       logger.error("error while converting to JSON");
       throw new RuntimeException(ioe);
      }
      
      logger.debug("converting successful");
      return writer.toString();
   }
}
