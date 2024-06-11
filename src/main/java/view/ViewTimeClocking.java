package view;

import TCP.TCPClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ViewTimeClocking extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("pointeuse.fxml"));
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Time Clocking Application");
        stage.setScene(scene);
        stage.show();

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

    private boolean isServerReady(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 1000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
