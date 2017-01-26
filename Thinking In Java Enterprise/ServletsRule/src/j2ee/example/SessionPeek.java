package j2ee.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class SessionPeek
 * to run this servlet - http://localhost:8080/ServletsRule/SessionPeek 
 */
@WebServlet("/SessionPeek")
public class SessionPeek extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
	  	//	To get object Session
		HttpSession session = req.getSession();
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.print("<head>");
		out.print("<title>SessionPeek</title>");
		out.print("</head><body>");
		out.print("<h3>SessionPeek</h3>");
	  	// Simple counter
		Integer ival = (Integer) session.getAttribute("sesspeek.cntr");
		if (ival == null)
			ival = new Integer(1);
		else
			ival = new Integer(ival.intValue() + 1);
		session.setAttribute("sesspeek.cntr", ival);
		out.print("You have hit this page <b>" + ival + "</b> times.");
		out.print("<p><b>Saved Session Data</b><br>");
		// Get all data from session
		Enumeration<String> sesNames = session.getAttributeNames();
		while (sesNames.hasMoreElements()) {
			String name = sesNames.nextElement();
			Object value = session.getAttribute(name);
			out.println(name + " = " + value + "<br>");
		}
		out.print("<p><b>Session Statistics</b><br>");
		out.print("Session ID: " + session.getId() + "<br>");
		out.print("New Session: " + session.isNew() + "<br>");
		out.print("Creation Time: " + session.getCreationTime());
		out.print("<i>(" + new Date(session.getCreationTime()) + ")</i><br>");
		out.print("Last Accessed Time: " + session.getLastAccessedTime());
		out.print("<I>(" + new Date(session.getLastAccessedTime()) + ")</i><br>");
		out.print("Session Inactive Interval: " + session.getMaxInactiveInterval());
		out.print("Session ID in Request: " + req.getRequestedSessionId() + "<br>");
		out.print("Is session id from Cookie: " + req.isRequestedSessionIdFromCookie() + "<br>");
		out.print("Is session id from URL: " + req.isRequestedSessionIdFromURL() + "<br>");
		out.print("Is session id valid: " + req.isRequestedSessionIdValid() + "<br>");
		out.print("<p><a href='.'>Back</a>");
		out.print("</body>");
		out.close();
	}

	public String getServletInfo() {
		return "A session tracking servlet";
	}
}