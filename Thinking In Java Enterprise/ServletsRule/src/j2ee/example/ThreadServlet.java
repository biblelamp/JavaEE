package j2ee.example;

import java.io.*;

//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class ThreadServlet
 * to run this servlet - http://localhost:8080/ServletsRule/ThreadServlet 
 */
@WebServlet("/ThreadServlet")
public class ThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int i;
	   
	/**
	 * Example with Tread
     */
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		synchronized (this) {
            try {
	            Thread.currentThread();
				Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            System.err.println("Interrupted");
	        }
	    }
		out.print("<h3>Finished " + i++ + "</h3>");
		out.print("<a href='.'>Back</a>");
	    out.close();
	}
}