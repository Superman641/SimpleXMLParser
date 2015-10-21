
package xmlparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class JacksonJSON
{
   public static String makeJSON(Envelope envelope)
   {
      Writer writer = new StringWriter();
      ObjectMapper mapper = new ObjectMapper();
      try 
      {   
         mapper.writeValue(writer, envelope);
      } 
      catch (IOException ioe) 
      {
       throw new RuntimeException(ioe);
      }
      
      return writer.toString();
   }
}
