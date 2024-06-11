package view;

import TCP.TCPClient;
import TCP.TCPServer;
import javafx.application.Application;
import javafx.collections.ObservableList;
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
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();

        // Démarrer le serveur TCP dans un thread séparé
        startTCPServer();

        // Démarrer le client TCP après un délai
        new Thread(() -> {
            try {
                // Attendre quelques secondes pour s'assurer que le serveur est démarré
                Thread.sleep(2000);
                TCPClient.sendMessage("Hello from TCP Client");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        openTimeClockingApplication();
    }

    private void startTCPServer() {
        tcpServer = new TCPServer();
        new Thread(() -> tcpServer.startServer()).start();
    }

    private void openTimeClockingApplication() throws IOException {
        Stage timeClockingStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("pointeuse.fxml"));
        Scene scene = new Scene(root);
        timeClockingStage.setTitle("Time Clocking Application");
        timeClockingStage.setScene(scene);
        timeClockingStage.show();
    }

    public void ouverture_fichier() {
        // Code pour ouvrir le fichier
    }

    public static void main(String[] args) {
        launch(args);
    }
}