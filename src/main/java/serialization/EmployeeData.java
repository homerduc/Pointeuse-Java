package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.Planning;

import java.time.LocalTime;

public abstract class EmployeeData {

    private static ObservableList<Employee> employeeList = Deserializer.deserializeEmployes("SauvegardeProjet.ser");

    //region GETTER & SETTER
    public static ObservableList<Employee> getEmployeeList() {return employeeList;}
    public static void setEmployeeList(ObservableList<Employee> list) {employeeList=list;}
    public static int getNextId() {
        if (employeeList.isEmpty()) {
            Employee New = new Employee(1,"Admin","Admin","Admin","Admin","Admin",0,new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0)));
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
    //endregion GETTER & SETTER

    //region BOUTONS
    public static void addEmployee(String first,String last,String post,String email,String tel, int delta){

        Employee newEmployee = new Employee(EmployeeData.getNextId(), first, last, post, email, tel, delta, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0)));
        employeeList.add(newEmployee);
    }
    public static void removeEmployee(Employee rmEmployee){
        employeeList.remove(rmEmployee);

    }
    public static void modifyEmployee(Employee employee,String first,String last,String post,String email,String tel, int delta){
        employee.setFirstName(first);
        employee.setLastName(last);
        employee.setPost(post);
        employee.setMail(email);
        employee.setTel(tel);
        employee.setDeltaWorkTime(delta);

    }
    //endregion

    public static void updateFile(){
        Serializer.serializeEmployees(employeeList,"SauvegardeProjet.ser");
    }
}
