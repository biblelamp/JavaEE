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

        if (code == null || name == null || price == null)
            // if no data
            RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/form.html");
        else
            // if have data
            RequestDispatcher dispatcher = this.getServletContext()
                .getRequestDispatcher("/form.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}