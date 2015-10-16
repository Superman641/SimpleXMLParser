package XMLtoJava;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author asus
 */
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
