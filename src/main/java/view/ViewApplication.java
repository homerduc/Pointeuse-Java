package view;

import TCP.TCPServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;

public class ViewApplication extends Application {
    private TCPServer tcpServer;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewApplication.class.getResource("main-pane-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Central Application");
        stage.setScene(scene);
        stage.show();

        startTCPServer();
    }

    private void startTCPServer() {
        tcpServer = new TCPServer(1234);  // Spécifiez le port ici
        new Thread(() -> {
            tcpServer.startServer();
            System.out.println("Server started successfully");
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
