package simplewebapp.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simplewebapp.beans.Product;
import simplewebapp.conn.ConnectionUtils;
import simplewebapp.utils.DBUtils;
import simplewebapp.utils.MyUtils;

@WebServlet(urlPatterns = {"/editProduct"})
public class EditProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProductServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String code = (String) request.getParameter("code");
        Product product = null;
        String errorString = null;

        try {
        	if (conn == null) // if conn was not stored
        		conn = ConnectionUtils.getConnection();
            product = DBUtils.findProduct(conn, code);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // if no error
        // the product does not exist to edit
        // redirect to productList page
        if (errorString != null && product == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }

        // store errorString in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("product", product);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/editProductView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}