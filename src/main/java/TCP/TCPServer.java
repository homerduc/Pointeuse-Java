package TCP;

import model.Employee;
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

            String[] msgArray=msg.split(" ");

            //Creation de la référence de l'employe
            Employee employee = EmployeeData.findEmployeeById(msgArray[0]);

            //Changement de l'attribut check in ou check out de la personne
            //employee.Etatducheck();  //!\\
            if(!employee.getCheck_in()){
                employee.setCheck_in(true);
            }
            else if (employee.getCheck_in()&&! employee.getCheck_out()) {
                employee.setCheck_out(true);
            }

//    création de l'objet timeclocking
            TimeClocking timeClocking= new TimeClocking(LocalDateTime.parse(msgArray[1],DateTimeFormatter.ISO_LOCAL_DATE_TIME),employee);

            return timeClocking;

        }

    }
}
