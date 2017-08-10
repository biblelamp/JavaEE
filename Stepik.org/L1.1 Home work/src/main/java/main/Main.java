package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * Home work 1 for stepik.org course
 *
 * @author Sergey Irupin
 * @version dated Aug 10, 2017
 */
public class Main {
    public static void main(String[] args) throws Exception {

        // Create a basic Jetty server object that will listen on port 8080
        Server server = new Server(8080);

        // Create the ResourceHandler. It is the object that will actually
        // handle the request for a given file. It is a Jetty Handler object
        // so it is suitable for chaining with other handlers as you will see
        // in other examples
        ResourceHandler resource_handler = new ResourceHandler();

        // Configure the ResourceHandler. Setting the resource base indicates
        // where the files should be served out of
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase("src/main/webapp/");

        // Add the ResourceHandler to the server
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
        server.setHandler(handlers);

        // Start server
        server.start();
        server.join();
    }
}