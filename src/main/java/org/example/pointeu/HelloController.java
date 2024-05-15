package org.example.pointeu;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
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

    private String[] filters= {"checked","NoCkecked","Late","Early"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        CB_TimeClocking.getItems().addAll(filters);
    }
}