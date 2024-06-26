package view;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Planning;
import serialization.EmployeeData;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The TodayClocking class manages the user interface for displaying and interacting with employee check-in and check-out status.
 * It includes functionalities for filtering employees, updating their status, and handling visual enhancements.
 */
public class TodayClocking implements Initializable {

    private static TodayClocking instance;

    public Button Info_but;

    @FXML
    public TextField reserch_field;
    @FXML
    public CheckBox Checkboxin;
    @FXML
    public CheckBox Checkboxout;
    @FXML
    public Button butRefresh;

    @FXML
    private TableView<Employee> Table;
    @FXML
    private TableColumn<Employee, Boolean> CHECKIN;
    @FXML
    private TableColumn<Employee, Boolean> CHECKOUT;
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

    private Timeline timeline;

    /**
     * Updates the TableView with the current employee data, applying filters if any, and initializes event listeners.
     */
    public void UpdateTable() {

        listemployee = EmployeeData.getData();


        FilteredList<Employee> filtreliste = new FilteredList<>(listemployee);
        reserch_field.textProperty().addListener((observable, oldValue,newValue)->{
            filtreliste.setPredicate(employee -> {

                Table.refresh();
                if( newValue.isEmpty() || newValue == null|| newValue.isBlank()){

                    Info_but.setDisable(false);

                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                return employee.getFirstName().toLowerCase().contains(lowerCaseFilter)
                        || employee.getLastName().toLowerCase().contains(lowerCaseFilter)
                        || String.valueOf(employee.getId()).contains(lowerCaseFilter);

            });
        });
        SortedList<Employee> SortedData = new SortedList<>(filtreliste);
        SortedData.comparatorProperty().bind(Table.comparatorProperty());

        EmployeeData.updateFile();
        Table.setItems(SortedData);//|listemployee

    }

    /**
     * Initializes the TodayClocking controller, setting up column cell value factories, event handlers, and initial data display.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
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
        CHECKIN.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("Check_in"));
        CHECKOUT.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("Check_out"));
        Raduibouton_couleur.setOnAction(event -> handleRadioButtonAction());

        // Ajout des écouteurs pour les CheckBox
        Checkboxin.setOnAction(event -> handleCheckInChange());
        Checkboxout.setOnAction(event -> handleCheckOutChange());

        Table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                Checkboxin.setSelected(newValue.getCheck_in());
                Checkboxout.setSelected(newValue.getCheck_out());
                if(!Checkboxin.isSelected()){
                    Checkboxout.setDisable(true);
                }
                else{
                    Checkboxout.setDisable(false);
                }

            }
        });

        UpdateTable();
    }

    /**
     * Handles the action when Info_but button is clicked.
     * Displays detailed information about the selected employee.
     */
    @FXML
    void setInfo_but() {
        Table.getSelectionModel().getSelectedItem().toString();
    }

    /**
     * Handles the action when Raduibouton_couleur RadioButton is selected.
     * Initiates or stops a timeline for visual enhancements based on employee status.
     */
    @FXML
    public void handleRadioButtonAction() {
        if(Raduibouton_couleur.isSelected()){

            EventHandler<ActionEvent> eventHandler = event -> {
                //Couleurs();
            };
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.3), eventHandler);
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
        else
        {
            refresh();
            stopTimeline();
        }
    }

    /**
     * Stops the running timeline for visual enhancements.
     */
    public void stopTimeline() {
        if (timeline != null) {
            timeline.stop();
        }
    }

//    @FXML                             //!\\ ne fonctionne que avec l'ancienne version ou on a des checkbox dans le tableau
//    public void Couleurs(){
//        for (Employee employee : Table.getItems())
//        {
//
//            if (employee == null)
//            {
//                employee.getCheck_in().getParent().setStyle("-fx-background-color: white");
//                employee.getCheck_out().getParent().setStyle("-fx-background-color: white");
//            }
//            else if (employee.getCheck_in() && employee.getCheck_out())
//            {
//                employee.getCheck_in().getParent().setStyle("-fx-background-color: green");
//                employee.getCheck_out().getParent().setStyle("-fx-background-color: green");
//            }
//            else if (employee.getCheck_in())
//            {
//                employee.getCheck_in().getParent().setStyle("-fx-background-color: orange");
//                employee.getCheck_out().getParent().setStyle("-fx-background-color: orange");
//            } else if(!employee.getCheck_in()&& !employee.getCheck_out())
//            {
//                employee.getCheck_in().getParent().setStyle("-fx-background-color: red");
//                employee.getCheck_out().getParent().setStyle("-fx-background-color: red");
//            }
//        }
//    }

    /**
     * Refreshes the TableView to update any visual changes.
     */
    @FXML
    public void refresh(){
        Table.refresh();
    }

    /**
     * Handles the action when Checkboxin CheckBox is selected/deselected.
     * Updates the check-in status of the selected employee.
     */
    @FXML
    public void handleCheckInChange() {
        Integer selectedEmployee = Table.getSelectionModel().getSelectedItem().getId();
        EmployeeData.findEmployeeById(String.valueOf(selectedEmployee)).setCheck_in(Checkboxin.isSelected());
        EmployeeData.updateFile();
        UpdateTable();


    }
    /**
     * Handles the action when Checkboxout CheckBox is selected/deselected.
     * Updates the check-out status of the selected employee.
     */
    @FXML
    public void handleCheckOutChange() {
        Integer selectedEmployee = Table.getSelectionModel().getSelectedItem().getId();
        EmployeeData.findEmployeeById(String.valueOf(selectedEmployee)).setCheck_out(Checkboxout.isSelected());
        EmployeeData.updateFile();
        UpdateTable();
    }



}
