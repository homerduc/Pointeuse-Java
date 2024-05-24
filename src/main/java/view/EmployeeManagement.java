package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import view.EmployeeData;
import model.Planning;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class EmployeeManagement implements Initializable {

    public TextField searchField;
    @FXML
    private TableView<Employee> Table_EM;
    @FXML
    private Button but_add;
    @FXML
    private Button but_modif;
    @FXML
    private Button but_suppr;
    @FXML
    private TableColumn<Employee, Integer> col_delta;
    @FXML
    private TableColumn<Employee, String> col_email;
    @FXML
    private TableColumn<Employee, String> col_fname;
    @FXML
    private TableColumn<Employee, Integer> col_id;
    @FXML
    private TableColumn<Employee, String> col_lname;
    @FXML
    private TableColumn<Employee, String> col_planning;
    @FXML
    private TableColumn<Employee, String> col_post;
    @FXML
    private TableColumn<Employee, String> col_tel;
    @FXML
    private TextField text_email;
    @FXML
    private TextField text_firstname;
    @FXML
    private TextField text_id;
    @FXML
    private TextField text_lastname;
    @FXML
    private TextField text_planing;
    @FXML
    private TextField text_post;
    @FXML
    private TextField text_tel;
    @FXML
    private TextField text_delta;

    private ObservableList<Employee> list_employee;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list_employee = EmployeeData.getEmployeeList();
        UpdateTable();
    }

    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("Id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("FirstName"));
        col_lname.setCellValueFactory(new PropertyValueFactory<Employee, String>("LastName"));
        col_post.setCellValueFactory(new PropertyValueFactory<Employee, String>("Post"));
        col_tel.setCellValueFactory(new PropertyValueFactory<Employee, String>("Tel"));
        col_email.setCellValueFactory(new PropertyValueFactory<Employee, String>("mail"));
        col_delta.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("DeltaWorkTime"));
        col_planning.setCellValueFactory(new PropertyValueFactory<Employee, String>("Planning"));
        Table_EM.setItems(list_employee);


    }

    @FXML
    void setBut_add() {
        int newId = EmployeeData.getNextId();
        Employee new_employee = new Employee(
                newId,
                text_firstname.getText(),
                text_lastname.getText(),
                text_post.getText(),
                text_email.getText(),
                text_tel.getText(),
                Integer.parseInt(text_delta.getText()),
                new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))
        );
        list_employee.add(new_employee);
        UpdateTable();
        text_id.clear();
        text_firstname.clear();
        text_lastname.clear();
        text_post.clear();
        text_email.clear();
        text_tel.clear();
        text_delta.clear();
    }

    @FXML
    void setBut_suppr(ActionEvent event) {
        int selectedID = Table_EM.getSelectionModel().getSelectedIndex();
        Table_EM.getItems().remove(selectedID);
        list_employee.removeIf(employee -> employee.getId() == selectedID + 1);
    }
}
