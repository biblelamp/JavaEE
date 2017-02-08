package tags;

import java.util.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PrimeNumTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private int start = 0;
	private int end = 0;
	ArrayList<Integer> primeList;
	Iterator<Integer> primeIterator;

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int doStartTag() throws JspException {
		try {
			//primeList = PrimeUtilities.sievePrimes(start, end);
			primeList = new ArrayList<>();
			for (int i = start; i <= end; i++) primeList.add(i);
			primeIterator = primeList.iterator();
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_BODY;
		}
		return EVAL_BODY_BUFFERED;
	}

	public void doInitBody() throws JspException {
		try {
			if (primeIterator.hasNext()) {
				pageContext.setAttribute("value", primeIterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	   
	public int doAfterBody() {
		try {
			if (primeIterator.hasNext()) {
				pageContext.setAttribute("value", primeIterator.next());
				return EVAL_BODY_BUFFERED;
			} else {
				bodyContent.writeOut(bodyContent.getEnclosingWriter());
				return SKIP_BODY;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SKIP_BODY;
		}
	}

	public void release() {
		primeList = null;
		start = 0;
	}
}