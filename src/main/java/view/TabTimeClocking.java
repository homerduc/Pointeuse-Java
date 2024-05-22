package view;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.Planning;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class TabTimeClocking implements Initializable {

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
    private Button Checked;

    @FXML
    private Button history;

    @FXML
    private Button today;

    @FXML
    private Button unchecked;

    @FXML
    private ComboBox<?> filtres;


    ObservableList<Employee> liste = FXCollections.observableArrayList(
        new Employee(1,"michel","fromage","cadre","michel.fromage@gmail.com","0625487962",12,new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
        new Employee(2,"michel","boulet","cadre","michel.fromage@gmail.com","2536145869",12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
        new Employee(3,"paula","moret","employe","paula.moret@gmail.com","1425369685",5, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
        new Employee(4,"marcel","brique","employe","marcel.brique@gmail.com","0230215456",2, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
        new Employee(5,"christine","chapeau","cadre","christine.chapeau@gmail.com","5456892123",12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0)))
    );

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ID.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("Id"));
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
        Table.setItems((liste));
    }
}
