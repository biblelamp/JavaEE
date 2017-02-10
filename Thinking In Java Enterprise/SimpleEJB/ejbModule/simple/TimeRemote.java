package simple;

import javax.ejb.Remote;

//Annotation "@Remote" means that this interface describes
//the methods available for remote clients call
@Remote
public interface TimeRemote {
	// Returns a string with server time
	String getTime();
}