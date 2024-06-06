package view;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;

public class MainPane {
    @FXML
    public Tab HistoryOfAllTime;
    @FXML
    private Tab EmployeeManagement;

    @FXML
    private Tab TodayCloking;

    @FXML
    void Colorhoover(){
        if(TodayCloking.isSelected()){
            EmployeeManagement.setStyle("-fx-background-color: #568a70;");
            HistoryOfAllTime.setStyle("-fx-background-color: #568a70;");
            TodayCloking.setStyle("-fx-background-color: #75bb99;");
        }
        else if(HistoryOfAllTime.isSelected()) {
            TodayCloking.setStyle("-fx-background-color: #568a70;");
            EmployeeManagement.setStyle("-fx-background-color: #568a70;");
            HistoryOfAllTime.setStyle("-fx-background-color: #75bb99;");
        }
        else{
            EmployeeManagement.setStyle("-fx-background-color: #75bb99;");
            HistoryOfAllTime.setStyle("-fx-background-color: #568a70;");
            TodayCloking.setStyle("-fx-background-color: #568a70;");
        }
    }
}
