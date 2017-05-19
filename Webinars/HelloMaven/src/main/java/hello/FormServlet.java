package hello;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

		// get data from request
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String price = request.getParameter("price");

		// prepared to write as html
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
			
		if (code == null || name == null || price == null) {
			// no data
			pw.println("<!DOCTYPE html>\n"+
			"<html>\n"+
			"<head>\n"+
			"<meta charset='UTF-8'>\n"+
			"<title>GetForm Servlet</title>\n"+
			"</head>\n"+
			"<body>\n"+
			"<h2>GetForm Servlet:</h2>\n"+
			"<form method='POST' action='FormServlet'>\n"+
			"<table border='0'>\n"+
			"<tr>\n"+
			"<td>Code</td>\n"+
			"<td><input type='text' name='code'/></td>\n"+
			"</tr>\n"+
			"<tr>\n"+
			"<td>Name</td>\n"+
			"<td><input type='text' name='name'/></td>\n"+
			"</tr>\n"+
			"<tr>\n"+
			"<td>Price</td>\n"+
			"<td><input type='text' name='price'/></td>\n"+
			"</tr>\n"+
			"<tr>\n"+
			"<td colspan='2'>\n"+           
			"<input type='submit' value='Submit'/>\n"+
			"</td>\n"+
			"</tr>\n"+
			"</table>\n"+
			"</form>\n"+
			"Go <a href='"+
				request.getServletContext().getContextPath()+
			"'>Back</a>\n"+
			"</body>\n"+
			"</html>");
		} else {
			// have data
			pw.println("<!DOCTYPE html>\n"+
			"<html>\n"+
			"<head>\n"+
			"<meta charset='UTF-8'>\n"+
			"<title>GetForm Servlet</title>\n"+
			"</head>\n"+
			"<body>\n"+
			"<h2>GetForm Servlet:</h2>\n"+
			"<table border='0'>\n"+
			"<tr>\n"+
			"<td>Code</td>\n"+
			"<td><b>" + code + "</b></td>\n"+
			"</tr>\n"+
			"<tr>\n"+
			"<td>Name</td>\n"+
			"<td><b>" + name + "</b></td>\n"+
			"</tr>\n"+
			"<tr>\n"+
			"<td>Price</td>\n"+
			"<td><b>" + price + "</b></td>\n"+
			"</tr>\n"+
			"</table>\n"+
			"Go <a href='"+
				request.getServletContext().getContextPath()+
			"'>Back</a>\n"+
			"</body>\n"+
			"</html>");
		}
		pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}
