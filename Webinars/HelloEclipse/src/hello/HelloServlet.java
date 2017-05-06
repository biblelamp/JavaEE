package hello;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/HelloServlet")
public class HelloServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;

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