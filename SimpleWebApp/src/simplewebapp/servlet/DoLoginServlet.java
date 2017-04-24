package simplewebapp.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import simplewebapp.beans.UserAccount;
import simplewebapp.utils.DBUtils;
import simplewebapp.utils.MyUtils;

@WebServlet(urlPatterns = {"/doLogin" })
public class DoLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoLoginServlet() {
		super();
	}

	@Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember= "Y".equals(rememberMeStr);

        UserAccount user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null
                 || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                user = DBUtils.findUser(conn, userName, password);
                if (user == null) {
                    hasError = true;
                    errorString = "User Name or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }

        // if error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);

            // store information in request attribute, before forward
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        }

        // if no error
        // store user information in Session
        // and redirect to userInfo page
        else {
        	HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);

            // if user checked "Remember me"
            if(remember)  {
                MyUtils.storeUserCookie(response, user);
            }

            // else delete cookie
            else  {
                MyUtils.deleteUserCookie(response);
            }

            // redirect to userInfo page
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

	@Override
	protected void doPost(HttpServletRequest request,
    		HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}