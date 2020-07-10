import service.HelloWorldImpl;

import javax.xml.ws.Endpoint;

// Endpoint publisher
public class HelloWorldPublisher {

    // test it by call http://localhost:8080/ws/hello?wsdl

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/hello", new HelloWorldImpl());
    }

}