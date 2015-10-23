package xmlparser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@WebServlet(name = "XMLParserServlet", urlPatterns = "/XMLParserServlet")
public class XMLParserServlet extends HttpServlet {
   
   //initializing logger
   public static final InputStream logis = Bootstrap.class.getResourceAsStream("../properties/log4j.properties");
   private static Logger logger = Logger.getLogger(XMLParser.getCurrentClassName());
   static{PropertyConfigurator.configure(logis);}
   
   //variable
   private byte[] jsonstream;
   
   //getter just in case
   public byte[] getJson(){return jsonstream;}
   
   //methog printing all this HTML stuff
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      try {
         out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet XMLParserServlet</title>");         
         out.println("</head>");
         out.println("<body>");
         out.println("<div>Insert path of XML file</div>\n" +
"         <form action=\"index.html\" method=\"POST\">\n" +
"         <input type=\"text\" name=\"xml\"> \n" +
"         <input type=\"submit\">\n" +
"         </form>");
         out.println("</body>");
         out.println("</html>");
      } finally {
         out.close();
      }
   }
   
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      processRequest(request, response);
   }

   
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
           
           logger.debug("servlet started");
           //getting pathname, specified by user
           String path = (String)request.getParameter("xml");
           if(path == null) processRequest(request, response);
           
           ///parsing and converting data
           String json = JacksonJSON.makeJSON(XMLParser.validateXML(new File(path)));
           jsonstream = json.getBytes();
           logger.info("converted data:" + json);
           
           //creating socket on IP and port specified in config file
           Socket s = 
             new Socket(Bootstrap.properties.getProperty("tcp.dest.addr"),
                        Integer.parseInt(Bootstrap.properties.getProperty("tcp.dest.port")));
           
           //convert JSON data in HEX binary
           HexBinaryAdapter ha = new HexBinaryAdapter();
           logger.info("HEX data:" + ha.marshal(jsonstream));
           
           //sending JSON data
           s.getOutputStream().write(jsonstream);
           
           //don't know why it should be that way, but it works
           byte buf[] = new byte[64*1024];
           
           //printing response of TCP/IP 
           s.getInputStream().read(buf);
           response.getOutputStream().print(new String(buf));
   }  

   @Override
   public String getServletInfo() {
      return Arrays.toString(jsonstream);
   }// </editor-fold>
}
