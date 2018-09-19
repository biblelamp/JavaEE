package client;
/**
 * Client for web service
 *
 * @author Sergey Iryupin
 * @version dated Sep 19, 2018
 *
 * Code based on
 * https://javarush.ru/groups/posts/1168-veb-servisih-shag-1-chto-takoe-veb-servis-i-kak-s-nim-rabotatjh
 * Note:
 * use JDK 7
 */
import service.HelloWebService;

import java.net.URL;
import java.net.MalformedURLException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWebServiceClient {

	public static void main(String[] args) throws MalformedURLException {

		// get url
		URL url = new URL("http://localhost:1080/hello?wsdl");

		// take the data from the address
		// http://localhost:1080/hello
		QName qname = new QName("http://service/", "HelloWebServiceImplService");
		// create service
		Service service = Service.create(url, qname);
		// get port from service
		HelloWebService hello = service.getPort(HelloWebService.class);
		// get remote method
		System.out.println(hello.getHelloString("Java"));
	}
}