package TCP;

import java.io.*;
import java.net.*;

public class TCPClient {
    private final String serverAddress;
    private final int serverPort;

    public TCPClient(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

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
