package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.Planning;
import model.TimeClocking;

import java.time.LocalTime;

public abstract class EmployeeData {

    private static ObservableList<Employee> employeeList = Deserializer.deserializeEmployes("SaveEmployees.ser");
//    private static ObservableList<Employee> employeeList = FXCollections.observableArrayList(
//            new Employee(1,"michel","fromage","cadre","michel.fromage@gmail.com","0625487962",12,new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
//            new Employee(2,"michel","boulet","cadre","michel.fromage@gmail.com","2536145869",12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
//            new Employee(3,"paula","moret","employe","paula.moret@gmail.com","1425369685",5, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
//            new Employee(4,"marcel","brique","employe","marcel.brique@gmail.com","0230215456",2, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
//            new Employee(5,"christine","chapeau","cadre","christine.chapeau@gmail.com","5456892123",12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0)))
//    );
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
        Serializer.serializeEmployees(employeeList, "SaveEmployees.ser");
    }

    public static ObservableList<TimeClocking> getData() {
        return Deserializer.deserializePointages("SaveEmployees.ser");
    }
}
