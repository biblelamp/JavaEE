package publish;
/**
 * Class for service publishing
 *
 * @author Sergey Iryupin
 * @version dated Sep 19, 2018
 *
 * Code based on
 * https://javarush.ru/groups/posts/1168-veb-servisih-shag-1-chto-takoe-veb-servis-i-kak-s-nim-rabotatjh
 * Note:
 * use JDK 7
 */
import javax.xml.ws.Endpoint;

import service.HelloWebServiceImpl;

public class HelloWebServicePublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:1080/hello", new HelloWebServiceImpl());
	}
}