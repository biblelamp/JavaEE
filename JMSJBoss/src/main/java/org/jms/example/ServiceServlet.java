package org.jms.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class ServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Inject
	Sender sender;

	@Inject
	Reader reader;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
    		                 throws ServletException, IOException {
		String mode   = request.getParameter("mode"  );
		String prefix = request.getParameter("prefix");

		if (prefix == null || "".equals(prefix))
			prefix = "prefix";
    	
    	response.setContentType("text/html;charset=UTF-8");
    	PrintWriter out = response.getWriter();
		if (mode.equalsIgnoreCase("send")) {
	    	out.print("send messages :<br />");
	    	for (int i = 0; i < 3; i++) {
	    		String msg = prefix + " #" + i;
	    		out.print("&bull; " + msg + "<br />");
	    		sender.sendMessage(msg);
	    	}
	    	out.close();
		} if (mode.equalsIgnoreCase("list")) {
			out.print("list messages :<br />");
			List<String> messages = reader.readMessages("java:/jms/queue/ExpiryQueue");
			for (String str : messages) {
				out.print("&mdash; " + str + "<br />");
			}
			out.close();
		} else {
	    	out.print("receive messages :<br />");
	    	if (Receiver.messages.size() > 0) {
	    		for (int i = 0; i < Receiver.messages.size(); i++) {
		    		out.print("&mdash; " + Receiver.messages.get(i) + "<br />");
	    		}
	    		Receiver.messages.clear();
	    	}
	    	out.close();
		}
    }
}