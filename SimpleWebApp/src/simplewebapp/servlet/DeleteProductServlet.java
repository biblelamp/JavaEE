package simplewebapp.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simplewebapp.conn.ConnectionUtils;
import simplewebapp.utils.DBUtils;
import simplewebapp.utils.MyUtils;

@WebServlet(urlPatterns = {"/deleteProduct"})
public class DeleteProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DeleteProductServlet() {
        super();
    }

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String code = (String) request.getParameter("code");
        String errorString = null;

        try {
        	if (conn == null) // if conn was not stored
        		conn = ConnectionUtils.getConnection();
            DBUtils.deleteProduct(conn, code);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // if an error redirected to an error page.
        if (errorString != null) {

            // Store the information in the request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            //
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/deleteProductErrorView.jsp");
            dispatcher.forward(request, response);
        }

        // if everything good
        // Redirect to the product listing page        
        else {
            response.sendRedirect(request.getContextPath() + "/productList");
        }
    }

    @Override
	protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}