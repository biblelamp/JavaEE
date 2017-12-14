package hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
            "Go <a href='"+
                request.getServletContext().getContextPath()+
            "'>Back</a>\n"+
            "</body>\n"+
            "</html>");
        pw.close();
    }
}