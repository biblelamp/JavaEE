package hello;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

        String page; // page for forwarding

        // get data from request
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        if (code == null || name == null || price == null)
            page = "/form.html"; // if no data
        else
            page = "/form.jsp"; // if have data

        // get the RD object for redirecting
        RequestDispatcher rd = getServletContext().getRequestDispatcher(page);

        // redirect to page
        rd.forward(request, response);
    }

	protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}