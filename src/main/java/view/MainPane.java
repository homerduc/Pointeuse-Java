package view;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;

/**
 * The MainPane class controls the main UI pane with tabs for different functionalities.
 * It provides functionality to change the background color of tabs based on their selection state.
 */
public class MainPane {
    @FXML
    public Tab HistoryOfAllTime;
    @FXML
    private Tab EmployeeManagement;

    @FXML
    private Tab TodayCloking;

    /**
     * Changes the background color of tabs based on their selection state.
     * If TodayCloking tab is selected, sets its background color to green and others to default color.
     * If HistoryOfAllTime tab is selected, sets its background color to green and others to default color.
     * If neither are selected, sets EmployeeManagement tab background color to green and others to default color.
     */
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
