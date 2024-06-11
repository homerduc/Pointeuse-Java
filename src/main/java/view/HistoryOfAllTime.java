package view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Planning;
import model.TimeClocking;
import serialization.TimeClockingData;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class HistoryOfAllTime {

    @FXML
    public TableColumn<TimeClocking, String> columnName;
    @FXML
    public TableColumn<TimeClocking, String> columnNature;
    @FXML
    public TableColumn<TimeClocking, String> columnSchedule;
    @FXML
    public TableColumn<TimeClocking, String> columnDatetime;
    @FXML
    public TableColumn<TimeClocking, Integer> columnDelta;
    @FXML
    public TableView TableTimeCloking;

    private FilteredList<TimeClocking> filteredList;
    private ObservableList<TimeClocking> listPointages;
    @FXML
    public TextField reserch_field;


    public void UpdateTable() {
        listPointages = TimeClockingData.getTimeClockingList();
//        FilteredList<TimeClocking> filtreliste = new FilteredList<>(listPointages);
//        reserch_field.textProperty().addListener((observable, oldValue,newValue)->{
//            filtreliste.setPredicate(pointage -> {
//
//                TableTimeCloking.refresh();
//
//                String lowercaseFilter = newValue.toLowerCase();
//                if (pointage.getEmployee().getFirstName().toLowerCase().indexOf(lowercaseFilter)>-1)
//                {
//                    return true;
//                }
//                else{
//
//                    return false;
//                }
//
//            });
//        });
//        SortedList<TimeClocking> SortedData = new SortedList<>(filtreliste);
//        SortedData.comparatorProperty().bind(TableTimeCloking.comparatorProperty());

        TableTimeCloking.setItems(listPointages);//|listemployee

    }

    public void initialize(URL url, ResourceBundle rb) {

        columnName.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            String fullName = pointage.getEmployee().getFirstName() + " " + pointage.getEmployee().getLastName();
            return new ReadOnlyStringWrapper(fullName);
        });
        columnSchedule.setCellValueFactory(cellData -> {
            TimeClocking pointage = cellData.getValue();
            Planning planning = pointage.getEmployee().getPlanning();
            String planningString = planning.toString();
            return new ReadOnlyStringWrapper(planningString);
        });

        UpdateTable();
    }

}

