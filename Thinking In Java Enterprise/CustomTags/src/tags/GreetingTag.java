package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class GreetingTag implements Tag {
	private String id;
	private PageContext pageContext;

	public GreetingTag() {
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	@Override
	public void setParent(Tag parent) {
	}

	@Override
	public Tag getParent() {
		return null;
	}

	@Override
	public int doStartTag() throws JspException {
		pageContext.setAttribute(id, new GreetingBean(), PageContext.PAGE_SCOPE);
	    return Tag.SKIP_BODY;
   }

	@Override
	public int doEndTag() throws JspException {
		return Tag.EVAL_PAGE;
	}

	@Override
	public void release() {
	}
}