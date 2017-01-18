package jsf.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

// Annotations managed bean component
@ManagedBean (name="Main") // definition of managed bean and its name
@SessionScoped			   // definition lifetime - session
public class MainBean {

	private String login;
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 
	/* Simple Authorisation
	 * Check username and password 
	 * The check result - the name of the page
	 */
	public String checkLogin(){
		if (login.equalsIgnoreCase("root") && password.equalsIgnoreCase("root")) {
			return "loginsuccess?faces-redirect=true";
		} else {
			return "loginfailed?faces-redirect=true";
		}
	}
}
