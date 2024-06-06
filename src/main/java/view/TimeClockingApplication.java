package view;



import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TimeClockingApplication implements Initializable {
    @FXML
    public Button bouton_history;
    public ComboBox<String> CB_TimeClocking;
    public Button bouton_Today;
    public TableColumn TableViewTimeCloking;
    public Button bouton_Checked;
    public Button bouton_unchecked;
    public TableColumn TableView_employeeManagement;
    public Button button_add;
    public Button boutton_remove;
    public Button bouton_modify;
    public Tab TabSettings;
    public Tab TabEmployeeManagement;
    public Tab TabTimeClocking;
    public Label dateTimeLabel;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        updateDateTimeLabel();
    }
    @FXML
    private void updateDateTimeLabel() {
        if (dateTimeLabel != null) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime roundedTime = roundToNearestQuarterHour(now);

            String formattedDate = now.format(dateFormatter);
            String formattedTime = roundedTime.format(timeFormatter);

            String formattedDateTime = formattedDate + "\n" + formattedTime;

            dateTimeLabel.setText(formattedDateTime);
        }
    }


    private LocalDateTime roundToNearestQuarterHour(LocalDateTime dateTime) {
        int minute = dateTime.getMinute();
        int roundedMinute;
        if (minute < 8) {
            roundedMinute = 0;
        } else if (minute < 23) {
            roundedMinute = 15;
        } else if (minute < 38) {
            roundedMinute = 30;
        } else if (minute < 53) {
            roundedMinute = 45;
        } else {
            roundedMinute = 0;
            dateTime = dateTime.plusHours(1); // If minutes are between 53-59, round up to the next hour
        }
        return dateTime.withMinute(roundedMinute).withSecond(0).withNano(0);
    }


}

