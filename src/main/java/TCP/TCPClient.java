package TCP;

import java.io.*;
import java.net.*;

/**
 * The TCPClient class represents a client that communicates with a server over TCP/IP.
 */
public class TCPClient {
    private final String serverAddress;
    private final int serverPort;

    /**
     * Constructs a TCPClient with the specified server address and port.
     *
     * @param serverAddress the IP address or hostname of the server
     * @param serverPort the port number of the server
     */
    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /**
     * Sends a message to the server and receives a response.
     *
     * @param message the message to send to the server
     * @throws IOException if an I/O error occurs while sending or receiving data
     */
    public static void sendMessage(String message) throws IOException {
        try {
            System.out.println("Connecting to server...");
            Socket clientSocket = new Socket("localhost", 1234);
            System.out.println("Connected to server");


            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
            System.out.println("Message sent to server: " + message);


            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String response = in.readLine();
            System.out.println("Server response: " + response);


            clientSocket.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();

            throw e;
        }
    }
}
