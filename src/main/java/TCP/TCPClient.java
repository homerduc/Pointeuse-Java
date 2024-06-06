package TCP;

import java.io.*;
import java.net.*;

public class TCPClient {
    private static final String SERVER_ADDRESS = "localhost"; // Adresse IP du serveur
    private static final int SERVER_PORT = 1234; // Port utilisé par le serveur

    public static void sendMessage(String message) {
        try {
            System.out.println("Connecting to server...");
            Socket clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to server");

            // Envoyer le message au serveur
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(message);
            System.out.println("Message sent to server: " + message);

            // Lire la réponse du serveur
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Fermer la connexion avec le serveur
            clientSocket.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
