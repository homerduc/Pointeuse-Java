package view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TimeClocking;
import serialization.EmployeeData;
import serialization.TimeClockingData;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * The HistoryOfAllTime class controls the user interface for displaying and filtering time clocking data.
 * It provides functionality to update the table view based on user input and filter criteria.
 */
public class HistoryOfAllTime implements Initializable {

    public Button test_but;
    @FXML
    private TableColumn<TimeClocking, String> columnName;
    @FXML
    private TableColumn<TimeClocking, String> columnNature;
    @FXML
    private TableColumn<TimeClocking, String> columnSchedule;
    @FXML
    private TableColumn<TimeClocking, String> columnDatetime;
    @FXML
    private TableColumn<TimeClocking, String> columnDelta;
    @FXML
    private TableView<TimeClocking> TableTimeCloking;
    @FXML
    private TextField reserch_field;

    private FilteredList<TimeClocking> filteredList;

    /**
     * Updates the table view with the latest time clocking data.
     * Applies filtering based on the contents of the research field.
     */
    public void updateTable() {

        ObservableList<TimeClocking> listPointages = TimeClockingData.getData();

        filteredList = new FilteredList<>(listPointages, p -> true);
        reserch_field.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(pointage -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (pointage.getEmployee().getFirstName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pointage.getEmployee().getLastName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                else if (pointage.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")).contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<TimeClocking> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(TableTimeCloking.comparatorProperty());

        TableTimeCloking.setItems(sortedData);
    }

    /**
     * Initializes the controller class.
     * Sets up cell value factories for table columns and updates the table view with initial data.
     *
     * @param url the location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TimeClockingData.updateData();


        columnName.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String fullName = pointage.getEmployee().getFirstName() + " " + pointage.getEmployee().getLastName();
            return new ReadOnlyStringWrapper(fullName);
        });

        columnDatetime.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String datetimeString = pointage.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));
            return new ReadOnlyStringWrapper(datetimeString);
        });

        columnNature.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String answer = "";
            if(!pointage.getEmployee().getCheck_out()&&pointage.getEmployee().getCheck_in()){
                answer = "IN";
            }
            else if (pointage.getEmployee().getCheck_out()){
                answer = "out";
            }
            else if (!pointage.getEmployee().getCheck_out()&&!pointage.getEmployee().getCheck_in()){
                answer = "null";
            }
            return new ReadOnlyStringWrapper(answer);
        });
        columnSchedule.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String schedule = pointage.getEmployee().getPlanning().toString();
            return new ReadOnlyStringWrapper(schedule);
        });
        columnDelta.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String delta = String.valueOf(pointage.getEmployee().getDeltaWorkTime());
            return new ReadOnlyStringWrapper(delta);
        });
        updateTable();
    }

    /**
     * Handles the action when the "Update" button is clicked.
     * Updates the table view with the latest filtered data.
     */
    @FXML
    void button_update(){
        updateTable();

    }
}
