package view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Planning;

import java.net.URL;
import java.util.ResourceBundle;

public class TabTimeClocking implements Initializable {

    private static TabTimeClocking instance;

    public Button Info_but;

    public Button BUT_Refresh_Table_Colors;
    @FXML
    private TableView<Employee> Table;
    @FXML
    private TableColumn<Employee, CheckBox> CHECKIN;
    @FXML
    private TableColumn<Employee, CheckBox> CHECKOUT;
    @FXML
    private TableColumn<Employee, Integer> ID;
    @FXML
    private TableColumn<Employee, String> FULLNAME;
    @FXML
    private TableColumn<Employee, String> planning;
    @FXML
    private TextField Reherche;
    @FXML
    private Button history;
    @FXML
    private Button today;

    @FXML
    private ComboBox<?> filtres;

    private ObservableList<Employee> listemployee;

    public void UpdateTable() {
        listemployee = EmployeeData.getEmployeeList();
        Table.setItems(listemployee);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Id"));
        FULLNAME.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            String fullName = employee.getFirstName() + " " + employee.getLastName();
            return new ReadOnlyStringWrapper(fullName);
        });
        planning.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            Planning planning = employee.getPlanning();
            String planningString = planning.toString();
            return new ReadOnlyStringWrapper(planningString);
        });
        CHECKIN.setCellValueFactory(new PropertyValueFactory<Employee, CheckBox>("Check_in"));
        CHECKOUT.setCellValueFactory(new PropertyValueFactory<Employee, CheckBox>("Check_out"));


        UpdateTable();
    }

    @FXML
    void setInfo_but() {
        int selectedID = Table.getSelectionModel().getSelectedIndex();
        for(Employee search : listemployee){
            if(search.getId()== selectedID+1){
                search.toString();
            }
        }
    }

    @FXML
    public void setBUT_Refresh_Table_Colors(ActionEvent event) {
        for (Employee employee : listemployee) {
            if (employee.getCheck_in().isSelected() && employee.getCheck_out().isSelected())
            {
                employee.getCheck_in().getParent().setStyle("-fx-background-color: green;");
                employee.getCheck_out().getParent().setStyle("-fx-background-color: green;");
            } else if (employee.getCheck_in().isSelected())
            {
                employee.getCheck_in().getParent().setStyle("-fx-background-color: orange;");
                employee.getCheck_out().getParent().setStyle("-fx-background-color: orange;");
            }
            else{
                employee.getCheck_in().getParent().setStyle("-fx-background-color: red;");
                employee.getCheck_out().getParent().setStyle("-fx-background-color: red;");
            }
        }
    }

}
