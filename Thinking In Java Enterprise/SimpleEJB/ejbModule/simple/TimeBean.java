package simple;

import javax.ejb.Stateless;

//Annotation "@Stateless" indicates that this is a stateless bean
@Stateless
public class TimeBean implements TimeRemote {
	// implementation of an interface method
	public String getTime() {
		java.util.Date d = new java.util.Date();
		return "Server time is " + d;
	}
}