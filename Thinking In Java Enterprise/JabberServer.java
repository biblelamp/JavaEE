/*
 * Simple Server,
 * which sends back all that the client sends
 */
import java.io.*;
import java.net.*;

public class JabberServer {
    // Choose a port outside 1-1024
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Started: " + s);
        try {
            // Blocks as long as there is no connection
            Socket socket = s.accept();
            try {
                System.out.println("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
                while (true) {
                    String str = in.readLine();
                    if (str.equals("END"))
                        break;
                    System.out.println("Echoing: " + str);
                    out.println(str);
                }
            }
            finally {
                System.out.println("Closing...");
                socket.close();
            }
        }
        finally {
            s.close();
        }
    }
}