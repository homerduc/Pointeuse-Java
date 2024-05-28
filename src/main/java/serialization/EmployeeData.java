package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.Planning;

import java.time.LocalTime;

public abstract class EmployeeData {
    private static ObservableList<Employee> employeeList = Deserializer.deserializeEmployes("C:\\!Polytech\\Cycle ingé\\Semestre 6\\JAVA\\SauvegardeProjet.ser");

    public static ObservableList<Employee> getEmployeeList() {return employeeList;}
    public static void setEmployeeList(ObservableList<Employee> list) {employeeList=list;}

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

    public static void addEmployee(Employee NewEmployee){
        employeeList.add(NewEmployee);
        Serializer.serializeEmployees(employeeList,"C:\\!Polytech\\Cycle ingé\\Semestre 6\\JAVA\\SauvegardeProjet.ser");
    }
    public static void removeEmployee(Employee rmEmployee){
        employeeList.remove(rmEmployee);
        Serializer.serializeEmployees(employeeList,"C:\\!Polytech\\Cycle ingé\\Semestre 6\\JAVA\\SauvegardeProjet.ser");
    }
    public static void modifyEmployee(Employee employee,String first,String last,String post,String email,String tel, int delta){
        employee.setFirstName(first);
        employee.setLastName(last);
        employee.setPost(post);
        employee.setMail(email);
        employee.setTel(tel);
        employee.setDeltaWorkTime(delta);
        Serializer.serializeEmployees(employeeList,"C:\\!Polytech\\Cycle ingé\\Semestre 6\\JAVA\\SauvegardeProjet.ser");
    }
}
