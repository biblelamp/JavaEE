package tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.servlet.jsp.JspException;

public class DropCapitalTag extends BodyTagSupport implements TryCatchFinally {
	private static final long serialVersionUID = 1L;
	private String color = "black";

	public void setColor(String color) {
		this.color = color;
	}	

	public int doAfterBody() throws JspException {
		int period;
		StringBuffer result = new StringBuffer();
		String body = bodyContent.getString().trim();
		result.append("<table><tr><td><h1><font color='" + color + "'>");
		result.append(Character.toUpperCase(body.charAt(0)));
		result.append("</font></h1></td><td>");
		period = body.indexOf('.');
		result.append(body.substring(1, period + 1));
		result.append("</td></tr></table>");
		result.append(body.substring(period + 1, body.length()));
		try {
		    if (result.length() > 0) {
				bodyContent.clearBody();
				bodyContent.println(result.toString());
			}
	        bodyContent.writeOut(bodyContent.getEnclosingWriter());
		} catch (Exception e) {
	    	e.printStackTrace();
	    }
		return SKIP_BODY;
	}

	@Override
	public void doCatch(Throwable t) {
		System.out.println("An error occurred with the message" + t.getMessage());
	}

	@Override
	public void doFinally() {
	}

	public void release() {
		color = "black";
	}
}
