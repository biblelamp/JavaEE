package service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

// Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface HelloWorld {

    @WebMethod
    public String getHelloWorldAsString(String name);

}