package TCP;

import java.io.*;
import java.net.*;

public class TCPServer {
    private static final int PORT = 1234; // Port utilisé par le serveur
    private ServerSocket serverSocket;
    private boolean isRunning;

    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);
            isRunning = true;

            while (isRunning) {
                Socket clientSocket = serverSocket.accept(); // Accepter les connexions entrantes des clients
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Gérer la communication avec le client dans un nouveau thread
                Thread clientHandler = new Thread(new ClientHandler(clientSocket));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                isRunning = false;
                serverSocket.close();
                System.out.println("Server stopped");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Classe pour gérer les communications avec chaque client dans un thread séparé
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                // Lire les données envoyées par le client et répondre
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = in.readLine();
                System.out.println("Message received from client: " + message);

                // Envoyer une réponse au client
                out.println("Server received your message: " + message);

                // Fermer la connexion avec le client
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
