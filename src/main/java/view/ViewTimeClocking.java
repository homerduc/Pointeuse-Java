package view;

import TCP.TCPClient;
import TCP.TCPServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import model.Employee;
import serialization.EmployeeData;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

public class ViewTimeClocking extends Application {
    private TCPServer tcpServer;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pointeuse.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Time Clocking Application");
        stage.setScene(scene);
        stage.show();
        //startVerrificationTime();
        startTCPServer();

        new Thread(() -> {
            try {
                while (!isServerReady("localhost", 1234)) {
                    Thread.sleep(1000);
                }
                System.out.println("Server is ready, sending message");

                TCPClient.sendMessage("Hello from TCP Client");
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void startTCPServer() {
        tcpServer = new TCPServer(1234);
        new Thread(() -> {
            tcpServer.startServer();
            System.out.println("Server started successfully");
        }).start();
    }

    private boolean isServerReady(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 1000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (tcpServer != null) {
            tcpServer.stopServer();
        }
    }
    public void startVerrificationTime(){
        while(true){
            if(LocalDateTime.now().isEqual(ChronoLocalDateTime.from(LocalTime.of(0,0)))){
                for(Employee i : EmployeeData.getEmployeeList()){
                    i.setCheck_in(false);
                    i.setCheck_out(false);
                }
                EmployeeData.updateFile();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);


    }
}
