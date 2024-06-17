package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * The SettingsController class manages the application settings.
 * It provides functionality to save IP address and port settings to a file using serialization.
 */
public class SettingsController {

    @FXML
    private TextField ipAddressField;

    @FXML
    private TextField portField;

    /**
     * Saves the current IP address and port settings to a file.
     * Serializes the settings into a HashMap and writes it to "settings.ser".
     * If an IOException occurs during file writing, prints the stack trace.
     */
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


}
