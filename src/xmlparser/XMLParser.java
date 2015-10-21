package xmlparser;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLParser 
{
    public static Envelope validateXML(File inXML)
    {
      try 
      {
          JAXBContext jaxbContext = JAXBContext.newInstance(Envelope.class);
          Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
          Envelope envelope = (Envelope) jaxbUnmarshaller.unmarshal(inXML);

          return envelope;
      } 
      catch (JAXBException e) 
      {
          throw new RuntimeException(e);
      }
 }
}
