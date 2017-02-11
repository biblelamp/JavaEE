package simple;

import java.util.Date;

import javax.ejb.Stateless;

// Annotation "@Stateless" indicates that this is a stateless bean
@Stateless
public class TimeBean implements TimeRemote {

	// empty constructor
	public TimeBean() {
	}

	// implementation of an interface method
	@Override
	public String getTime() {
		Date d = new Date();
		return "Server time is " + d;
	}
}