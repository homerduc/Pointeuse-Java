package TCP;

import model.Employee;
import model.TimeClocking;
import serialization.EmployeeData;
import serialization.TimeClockingData;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The TCPServer class represents a TCP server that listens for incoming client connections
 * and handles communication with clients.
 */
public class TCPServer {
    private final int port;
    private ServerSocket serverSocket;
    private boolean isRunning;

    /**
     * Constructs a TCPServer that will listen on the specified port.
     *
     * @param port the port number on which the server will listen
     */
    public TCPServer(int port) {
        this.port = port;
    }

    /**
     * Starts the TCP server and begins accepting client connections.
     */
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

    /**
     * Stops the TCP server and closes the server socket.
     */
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

    /**
     * The ClientHandler class handles communication with a single client.
     */
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        /**
         * Constructs a ClientHandler with the given client socket.
         *
         * @param clientSocket the socket representing the client connection
         */
        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        /**
         * Handles communication with the client.
         */
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

        /**
         * Converts the received message into a TimeClocking object.
         *
         * @param msg the message received from the client
         * @return the TimeClocking object created from the message
         */
        public static TimeClocking convertMsgTimeClocking(String msg){

            String[] msgArray=msg.split(" ");

            //Creation de la référence de l'employe
            Employee employee = EmployeeData.findEmployeeById(msgArray[0]);

            //Changement de l'attribut check in ou check out de la personne
            //employee.Etatducheck();  //!\\ si on le met dans une autre classe que ici ca ne marche pas
                                       //!\\ pour voir le changement des attribut dans le premier tableau il faut relancer l'app

            if(!employee.getCheck_in()){
//                System.out.println("in avant"+ employee.getCheck_in());
                employee.setCheck_in(true);
//                System.out.println(employee.getFirstName()+employee.getLastName());
//                System.out.println("in apres"+ employee.getCheck_in());
                EmployeeData.updateFile();
            }
            else if (employee.getCheck_in()&&! employee.getCheck_out()) {
//                System.out.println("out avant"+ employee.getCheck_out());
                employee.setCheck_out(true);
//                System.out.println(employee.getFirstName()+employee.getLastName());
//                System.out.println("out apres"+ employee.getCheck_out());
                EmployeeData.updateFile();
            }
            LocalDateTime CheckTime = LocalDateTime.parse(msgArray[1],DateTimeFormatter.ISO_LOCAL_DATE_TIME);


//            Afficher le delta unique du pointage.
                //!\\ marche pas, faut faire un attribut delta dans timecloking

/*//            Pour le delta in
            LocalTime arriveRetard = LocalTime.of(8, 7);
            LocalTime arriveAvance = LocalTime.of(7, 53);
//            Pour le delta out
            LocalTime partirRetard= LocalTime.of(17, 7);
            LocalTime partirAvance = LocalTime.of(16, 53);

            if (CheckTime.isBefore(ChronoLocalDateTime.from(arriveAvance))&& employee.getCheck_in()){
                employee.setDeltaWorkTime(employee.getDeltaWorkTime()-1);
            }
            else if (CheckTime.isAfter(ChronoLocalDateTime.from(arriveRetard))&& employee.getCheck_in()){
                employee.setDeltaWorkTime(employee.getDeltaWorkTime()+1);
            }
            else if (CheckTime.isBefore(ChronoLocalDateTime.from(partirAvance))&& employee.getCheck_out()){
                employee.setDeltaWorkTime(employee.getDeltaWorkTime()+1);
            }
            else if (CheckTime.isAfter(ChronoLocalDateTime.from(partirRetard))&& employee.getCheck_out()){
                employee.setDeltaWorkTime(employee.getDeltaWorkTime()-1);
//            }*/


//création de l'objet timeclocking
            return new TimeClocking(CheckTime,employee);

        }

    }
}
