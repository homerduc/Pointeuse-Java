package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Employee;
import model.TimeClocking;
import serialization.EmployeeData;
import serialization.TimeClockingData;
import TCP.TCPClient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.String.valueOf;

public class TimeClockingApplication implements Initializable {

    @FXML
    private Button checkButton;
    @FXML
    private TextField employeeIdField;
    @FXML
    private Label dateTimeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDateTimeLabel();
        checkButton.setOnAction(event -> handleCheckInOut());
    }

    @FXML
    private void handleCheckInOut() {
        String employeeId = employeeIdField.getText();
        if (employeeId != null && !employeeId.isEmpty()) {
            Employee employee = EmployeeData.findEmployeeById(employeeId);
            if (employee != null) {
                try {
                   // DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd´T´HH:mm:ss");
                    TCPClient.sendMessage(valueOf(employee.getId())+ " "+ LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    System.out.println("Check-In: Employee ID - " + employee.getId() + " sent to server");
                } catch (IOException e) {
                    System.out.println("La connexion a échoué.");

                    saveLocalPointage(new TimeClocking(LocalDateTime.now(), employee));
                }
                employeeIdField.clear();
                updateDateTimeLabel();
            } else {
                System.out.println("Employé non trouvé");
            }
        }
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
            dateTime = dateTime.plusHours(1);
        }
        return dateTime.withMinute(roundedMinute).withSecond(0).withNano(0);
    }

    private void saveLocalPointage(TimeClocking timeClocking) {
        TimeClockingData.addTimeClocking(timeClocking);
        TimeClockingData.updateFile();
        if (timeClocking.getEmployee().getCheck_in()){
            timeClocking.getEmployee().setCheck_out(true);
        }
        else {
            timeClocking.getEmployee().setCheck_in(true);
        }

        EmployeeData.updateFile();
    }
}
