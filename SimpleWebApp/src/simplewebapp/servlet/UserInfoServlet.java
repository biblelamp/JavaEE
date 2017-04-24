package simplewebapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import simplewebapp.beans.UserAccount;
import simplewebapp.utils.MyUtils;

@WebServlet(urlPatterns = {"/userInfo" })
public class UserInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

	public UserInfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // check User has logged on
        UserAccount loginedUser = MyUtils.getLoginedUser(session);

        // not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // store info in request attribute
        request.setAttribute("user", loginedUser);

        // logined, forward to /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
	}
}