package hello;

import java.io.*;
import javax.servlet.*;

public class HelloServlet extends GenericServlet {

    public void service(ServletRequest request, ServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<!DOCTYPE html>");
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<meta charset='UTF-8'>");
        pw.println("<title>Hello Servlet</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h2>Hello Servlet!</h2>");
        pw.println("Go <a href='/HelloMaven'>Back</a>");
        pw.println("</body>");
        pw.println("</html>");
        pw.close();
    }
}