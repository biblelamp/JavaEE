package hello;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FormServlet extends HttpServlet {

    @Override
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

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}