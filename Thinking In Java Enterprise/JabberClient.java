/*
 * Simple Client,
 * which sends a string to the server and reads the response from the server
 */
import java.net.*;
import java.io.*;

public class JabberClient {

    public static void main(String[] args) throws IOException {
        // Transmit null в getByName(), to give
        // special IP address of "local stub"
        // for testing on a computer without a network
        InetAddress addr = InetAddress.getByName(null);
        // You can use address or name:
        // InetAddress addr = InetAddress.getByName("127.0.0.1");
        // InetAddress addr = InetAddress.getByName("localhost");
        System.out.println("Addr = " + addr);
        Socket socket = new Socket(addr, JabberServer.PORT);
        try {
            System.out.println("Socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())), true);
            for (int i = 0; i < 10; i++) {
                out.println("Howdy " + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        }
        finally {
            System.out.println("Closing...");
            socket.close();
        }
    }
}