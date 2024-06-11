package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class SettingsController {

    @FXML
    private TextField ipAddressField;

    @FXML
    private TextField portField;

    @FXML
    private void saveSettings() {
        String ipAddress = ipAddressField.getText();
        String port = portField.getText();

        Map<String, String> settings = new HashMap<>();
        settings.put("ipAddress", ipAddress);
        settings.put("port", port);

        try (FileOutputStream fos = new FileOutputStream("settings.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelSettings() {
        // Code pour fermer la fenêtre des paramètres
    }
}
