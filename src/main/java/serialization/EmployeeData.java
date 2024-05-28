package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.Planning;

import java.time.LocalTime;

public class EmployeeData {
    private static final ObservableList<Employee> employeeList = FXCollections.observableArrayList(
            new Employee(1, "michel", "fromage", "cadre", "michel.fromage@gmail.com", "0625487962", 12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
            new Employee(2, "michel", "boulet", "cadre", "michel.boulet@gmail.com", "2536145869", 12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
            new Employee(3, "paula", "moret", "employe", "paula.moret@gmail.com", "1425369685", 5, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
            new Employee(4, "marcel", "brique", "employe", "marcel.brique@gmail.com", "0230215456", 2, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
            new Employee(5, "christine", "chapeau", "cadre", "christine.chapeau@gmail.com", "5456892123", 12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0)))
    );

    public static ObservableList<Employee> getEmployeeList() {
        return employeeList;
    }
    public static int getNextId() {
        if (employeeList.isEmpty()) {
            return 1;
        }
        int maxId = employeeList.stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0);
        for (int i = 1; i <= maxId; i++) {
            int finalI = i;
            boolean idExists = employeeList.stream().anyMatch(employee -> employee.getId() == finalI);
            if (!idExists) {
                return i;
            }
        }
        return maxId + 1;
    }
}
