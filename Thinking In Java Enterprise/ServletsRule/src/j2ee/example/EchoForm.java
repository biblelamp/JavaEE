package j2ee.example;

import java.io.*;
import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class EchoForm
 * to run this servlet - http://localhost:8080/ServletsRule/EchoForm
 */
@WebServlet("/EchoForm")
public class EchoForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Make form, send and get it
     */
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		Enumeration<String> fields = req.getParameterNames();
		if (!fields.hasMoreElements()) {
			// to make the form
			out.print("<html>");
			out.print("<h3>Echo form:</h3>");
			out.print("<form method='POST' action='EchoForm'>");
			for (int i = 0; i < 10; i++)
				out.print("<b>Field" + i + "</b> " + "<input type='text'"
	                + " size='20' name='Field" + i + "' value='Value"
		            + i + "'><br>");
		    out.print("<input type='submit' name='submit'"
		    	+ " value='Submit'></form></html>");
		} else {
			// output the result of the form submission
			out.print("<h3>Your form contained:</h3>");
			while (fields.hasMoreElements()) {
				String field = fields.nextElement();
				String value = req.getParameter(field);
				out.print(field + " = " + value + "<br>");
			}
		}
		out.print("<a href='.'>Back</a>");
		out.close();
	}
}