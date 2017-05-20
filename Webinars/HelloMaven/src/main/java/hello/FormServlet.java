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

        if (code == null || name == null || price == null) {
            // if no data
            RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/form.html");
            dispatcher.forward(request, response);
        } else {
            // have data
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
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
            pw.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}