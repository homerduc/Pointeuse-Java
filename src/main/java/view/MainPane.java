package view;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import model.Employee;

public class MainPane {

    @FXML
    private Tab EmployeeManagement;

    @FXML
    private Tab TimeCloking;

    @FXML
    void Colorhoover(){
        if(TimeCloking.isSelected()){
            EmployeeManagement.setStyle("-fx-background-color: #568a70;");
            TimeCloking.setStyle("-fx-background-color: #75bb99;");
        }
        else {
            TimeCloking.setStyle("-fx-background-color: #568a70;");
            EmployeeManagement.setStyle("-fx-background-color: #75bb99;");
        }
    }
}
