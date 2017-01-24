package j2ee.example;

import java.io.*;
//import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class ServletsRule
 * to run this servlet - http://localhost:8080/ServletsRule/ServletsRule
 */
@WebServlet("/ServletsRule") // the name of servlet's page
public class ServletsRule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int i = 0;

    /**
     * To make simple HTML page
     * http://localhost:8080/ServletsRule/ServletsRule
     */
    public void service(HttpServletRequest req, HttpServletResponse res)
          throws IOException {
       res.setContentType("text/html");
       PrintWriter out = res.getWriter();
       out.print("<HEAD><TITLE>");
       out.print("A server-side strategy");
       out.print("</TITLE></HEAD><BODY>");
       out.print("<h1>Servlets Rule! " + i++);
       out.print("</h1></BODY>");
       out.close();
	}
}