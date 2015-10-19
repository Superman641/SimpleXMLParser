/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLtoJava;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
/**
 *
 * @author asus
 */
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
