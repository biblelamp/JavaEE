package simplewebapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
 
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

@WebServlet(urlPatterns = {"/productList" })
public class ProductListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

    	Connection conn = MyUtils.getStoredConnection(request);

        String errorString = null;
        List<Product> list = null;
        try {
        	if (conn == null) // if conn was not stored
        		conn = ConnectionUtils.getConnection();
            list = DBUtils.queryProduct(conn);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("productList", list);

        // forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/productListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
	protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}