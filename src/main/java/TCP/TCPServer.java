package TCP;

import model.TimeClocking;
import serialization.EmployeeData;
import serialization.TimeClockingData;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TCPServer {
    private final int port;
    private ServerSocket serverSocket;
    private boolean isRunning;

    public TCPServer(int port) {
        this.port = port;
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
            isRunning = true;

            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());


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


    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = in.readLine();
                System.out.println("Message received from client: " + message);

                out.println("Server received your message: " + message);

                // On redonne le contenu du fichier à la liste de TimeClockingData
                TimeClockingData.updateData();

                TimeClocking timeClocking = convertMsgTimeClocking(message);

                // On ajoute le nouveau TimeClocking à la liste
                TimeClockingData.addTimeClocking(timeClocking);

                // On écrase le fichier avec la liste mise à jour
                TimeClockingData.updateFile();

                clientSocket.close();
            } catch (Exception e) {
                if (e instanceof NullPointerException){
                    // ne rien faire
                }
                else if (e instanceof IOException){
                    e.printStackTrace();
                }

            }
        }
        public static TimeClocking convertMsgTimeClocking(String msg){
            //Création d'un objet time cloking
            TimeClocking timeClocking=new TimeClocking();
            String[] msgArray=msg.split(" ");
            timeClocking.setEmployee(EmployeeData.findEmployeeById(msgArray[0]));
            LocalDateTime dateTime=LocalDateTime.parse(msgArray[1],DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            timeClocking.setDateTime(dateTime);

            //Changement de l'attribut check in ou check out de la personne
            EmployeeData.changeChecked(timeClocking.getEmployee());

            // ajout du delta a la personne




            return timeClocking;

        }

    }
}
