package service;
/**
 * Interface for web service
 *
 * @author Sergey Iryupin
 * @version dated Sep 19, 2018
 *
 * Code based on
 * https://javarush.ru/groups/posts/1168-veb-servisih-shag-1-chto-takoe-veb-servis-i-kak-s-nim-rabotatjh
 * Note:
 * use JDK 7
 */
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

// interface will work as a web service
@WebService

// web service will be used to invoke methods
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWebService {

	// method can be called remotely
	@WebMethod
	String getHelloString(String name);
}