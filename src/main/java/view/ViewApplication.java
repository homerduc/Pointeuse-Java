package view;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;


import java.io.IOException;

public class ViewApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ViewApplication.class.getResource("main-pane-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Hello");
        stage.setScene(scene);
        stage.show();
        openTimeClockingApplication();
    }
    private void openTimeClockingApplication() throws IOException {
        Stage timeClockingStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("pointeuse.fxml"));
        Scene scene = new Scene(root);
        timeClockingStage.setTitle("Time Clocking Application");
        timeClockingStage.setScene(scene);
        timeClockingStage.show();
    }

    public void ouverture_fichier(){

    }
    public static void main(String[] args) {
        launch(args);
    }

}