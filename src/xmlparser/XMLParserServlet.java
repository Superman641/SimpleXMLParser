
package xmlparser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "XMLParserServlet", urlPatterns = "/XMLParserServlet")
public class XMLParserServlet extends HttpServlet {

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      try {
         /* TODO output your page here. You may use following sample code. */
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
          
           String path = (String)request.getParameter("xml");
           if(path == null) processRequest(request, response);
           
           String json = JacksonJSON.makeJSON(XMLParser.validateXML(new File(path)));
           response.getOutputStream().print(json);
           
   }  

   @Override
   public String getServletInfo() {
      return "Short description";
   }// </editor-fold>

}
