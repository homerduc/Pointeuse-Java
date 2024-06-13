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
    private TableColumn<TimeClocking, Integer> columnDelta;
    @FXML
    private TableView<TimeClocking> TableTimeCloking;
    @FXML
    private TextField reserch_field;

    private FilteredList<TimeClocking> filteredList;

    public void updateTable() {
        ObservableList<TimeClocking> listPointages = TimeClockingData.getTimeClockingList();

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
                return false;
            });
        });

        SortedList<TimeClocking> sortedData = new SortedList<>(filteredList);
        sortedData.comparatorProperty().bind(TableTimeCloking.comparatorProperty());

        TableTimeCloking.setItems(sortedData);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TimeClockingData.addTimeClocking(new TimeClocking(LocalDateTime.now(), EmployeeData.getEmployeeList().get(1)));

        columnName.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String fullName = pointage.getEmployee().getFirstName() + " " + pointage.getEmployee().getLastName();
            return new ReadOnlyStringWrapper(fullName);
        });

        columnDatetime.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String datetimeString = pointage.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm  [dd-MM-yyy] "));
            return new ReadOnlyStringWrapper(datetimeString);
        });

        columnNature.setCellValueFactory(new PropertyValueFactory<TimeClocking,String>("nature"));
        columnSchedule.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String schedule = pointage.getEmployee().getPlanning().toString();
            return new ReadOnlyStringWrapper(schedule);
        });
        columnDelta.setCellValueFactory(new PropertyValueFactory<TimeClocking,Integer>("delta"));

        updateTable();
    }
    @FXML
    void button_update(){
        TimeClockingData.updateFile();
        updateTable();
        System.out.println(TimeClockingData.getTimeClockingList().toString()); 
    }
}
