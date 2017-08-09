package main;

import org.eclipse.jetty.server.Server;

/**
 * Home work 1 for stepik.org course
 *
 * @author Sergey Irupin
 * @version dated Aug 09, 2017
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        server.start();
        server.join();
    }
}
