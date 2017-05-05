package hello;

import java.io.*;
import javax.servlet.*; // not in Java SE SDK set

public class HelloServlet extends GenericServlet {

    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<!DOCTYPE html>\n"+
            "<html>\n"+
            "<head>\n"+
            "<meta charset='UTF-8'>\n"+
            "<title>Hello Servlet</title>\n"+
            "</head>\n"+
            "<body>\n"+
            "<h2>Hello Servlet!</h2>\n"+
            "Go <a href='/HelloMaven'>Back</a>\n"+
            "</body>\n"+
            "</html>");
        pw.close();
    }
}