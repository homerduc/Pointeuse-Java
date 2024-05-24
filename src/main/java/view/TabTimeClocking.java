package view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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

    public TextField reserch_field;
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
    private RadioButton Raduibouton_couleur;

    private ObservableList<Employee> listemployee;

    private FilteredList<Employee> filteredList;

    public void UpdateTable() {
        listemployee = EmployeeData.getEmployeeList();
        FilteredList<Employee> filtreliste = new FilteredList<>(listemployee);
        reserch_field.textProperty().addListener((observable, oldValue,newValue)->{
            filtreliste.setPredicate(employee -> {

                Table.refresh();
                if( newValue.isEmpty() || newValue == null|| newValue.isBlank()){

                    Info_but.setDisable(false);
                    BUT_Refresh_Table_Colors.setDisable(false);
                    return true;
                }

                String lowercaseFilter = newValue.toLowerCase();
                if (employee.getFirstName().toLowerCase().indexOf(lowercaseFilter)>-1)
                {
                    Info_but.setDisable(true);
                    BUT_Refresh_Table_Colors.setDisable(true);
                    return true;
                }
                else if (employee.getLastName().toLowerCase().indexOf(lowercaseFilter)>-1)
                {
                    Info_but.setDisable(true);
                    BUT_Refresh_Table_Colors.setDisable(true);
                    return true;
                }
                else if (String.valueOf(employee.getId()).indexOf(lowercaseFilter)>-1)
                {
                    Info_but.setDisable(true);
                    BUT_Refresh_Table_Colors.setDisable(true);

                    return true;
                }
                else{

                    return false;
                }

            });
        });
        SortedList<Employee> SortedData = new SortedList<>(filtreliste);
        SortedData.comparatorProperty().bind(Table.comparatorProperty());

        Table.setItems(SortedData);

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
        Raduibouton_couleur.setOnAction(event -> handleRadioButtonAction());

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
    public void handleRadioButtonAction() {
        if(Raduibouton_couleur.isSelected()){
            Couleurs();
        }
        else
        {
            refresh();
        }
    }

    @FXML
    public void Couleurs(){
        for (Employee employee : Table.getItems())
        {

            if (employee == null)
            {
                employee.getCheck_in().getParent().setStyle("-fx-background-color: white");
                employee.getCheck_out().getParent().setStyle("-fx-background-color: white");
            }
            else if (employee.getCheck_in().isSelected() && employee.getCheck_out().isSelected())
            {
                employee.getCheck_in().getParent().setStyle("-fx-background-color: green");
                employee.getCheck_out().getParent().setStyle("-fx-background-color: green");
            }
            else if (employee.getCheck_in().isSelected())
            {
                employee.getCheck_in().getParent().setStyle("-fx-background-color: orange");
                employee.getCheck_out().getParent().setStyle("-fx-background-color: orange");
            } else if(!employee.getCheck_in().isSelected() && !employee.getCheck_out().isSelected())
            {
                employee.getCheck_in().getParent().setStyle("-fx-background-color: red");
                employee.getCheck_out().getParent().setStyle("-fx-background-color: red");
            }
        }
    }

    @FXML
    public void refresh(){
        Table.refresh();
    }




}
