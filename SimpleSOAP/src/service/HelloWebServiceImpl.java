package service;
/**
 * Class for implementing the interface
 *
 * @author Sergey Iryupin
 * @version dated Sep 19, 2018
 *
 * Code based on
 * https://javarush.ru/groups/posts/1168-veb-servisih-shag-1-chto-takoe-veb-servis-i-kak-s-nim-rabotatjh
 * Note:
 * use JDK 7
 */
import javax.jws.WebService;

@WebService(endpointInterface = "service.HelloWebService")
public class HelloWebServiceImpl implements HelloWebService {

	@Override
	public String getHelloString(String name) {
		return "Hello, " + name + "!";
	}
}