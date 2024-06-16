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


//            changer le delta de la personne. //!\\ marche pas, faut faire un attribut delta dans timecloking
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
