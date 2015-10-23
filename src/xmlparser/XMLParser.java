package xmlparser;

import java.io.File;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class XMLParser 
{
   //initializing logger
   public static final InputStream logis = Bootstrap.class.getResourceAsStream("../properties/log4j.properties");
   private static Logger logger = Logger.getLogger(XMLParser.getCurrentClassName());
   static{PropertyConfigurator.configure(logis);}
   
    public static Envelope validateXML(File inXML)
    {
      try 
      {
          //creating a context represented in Envelope object
          JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
               
         //converting XML data to envelope object
          Envelope envelope = (Envelope) jaxbUnmarshaller.unmarshal(inXML);

          logger.debug("parsing successful");
          return envelope;
      } 
      catch (JAXBException e) 
      {
          logger.error("error while parsing XML", e);
          throw new RuntimeException(e);
      }
 }
    
    public static String getCurrentClassName() //small util for getting class name in a static way
    {
       try
       {
          throw new RuntimeException();
       }
       catch(RuntimeException e)
       {
         return e.getStackTrace()[1].getClassName();
       }
    }
}
