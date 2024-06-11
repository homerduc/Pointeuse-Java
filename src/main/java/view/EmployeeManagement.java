package view;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import serialization.Deserializer;
import serialization.EmployeeData;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
public class EmployeeManagement implements Initializable {

    //region ATTRIBUTS
    public TextField searchField;
    @FXML
    public Button but_clear;
    @FXML
    public Button butCommit;
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
    //endregion

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list_employee = EmployeeData.getEmployeeList();

        Table_EM.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                text_id.setText(String.valueOf(newValue.getId()));
                text_firstname.setText(newValue.getFirstName());
                text_lastname.setText(newValue.getLastName());
                text_post.setText(newValue.getPost());
                text_email.setText(newValue.getMail());
                text_tel.setText(newValue.getTel());
                text_delta.setText(String.valueOf(newValue.getDeltaWorkTime()));
            }
        });
        text_id.setEditable(false);
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

    //region BOUTONS
    @FXML
    void setBut_add() {

        EmployeeData.addEmployee(text_firstname.getText(),text_lastname.getText(),text_post.getText(),text_email.getText(),text_tel.getText(),Integer.parseInt(text_delta.getText()));
        UpdateTable();
        clearTextfields();
        EmployeeData.updateFile();
    }
    @FXML
    void setBut_suppr(ActionEvent event) {
        EmployeeData.removeEmployee(Table_EM.getSelectionModel().getSelectedItem());
        UpdateTable();
        EmployeeData.updateFile();
    }
    @FXML
    void setBut_modif(){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous continuer?");
        ButtonType buttonYes = new ButtonType("Oui");
        ButtonType buttonNo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonYes, buttonNo);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == buttonYes) {

            EmployeeData.modifyEmployee(Table_EM.getSelectionModel().getSelectedItem(),
                    text_firstname.getText(),
                    text_lastname.getText(),
                    text_post.getText(),
                    text_email.getText(),
                    text_tel.getText(),
                    Integer.parseInt(text_delta.getText())
            );
            Table_EM.refresh();
            UpdateTable();

            System.out.println("l'ADMIN a cliqué sur Oui.");
        } else {
            System.out.println("l'ADMIN a cliqué sur Non.");
        }
        UpdateTable();
        EmployeeData.updateFile();
        clearTextfields();
    }
    @FXML
    void clearTextfields(){
        text_id.clear();
        text_firstname.clear();
        text_lastname.clear();
        text_post.clear();
        text_email.clear();
        text_tel.clear();
        text_delta.clear();
    }

}
