package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.Planning;
import model.TimeClocking;

import java.time.LocalTime;

public abstract class EmployeeData {

    /**
     * This class provides methods to manage Employee data including adding, removing,
     * modifying employees, and updating the serialized file.
     */
    private static ObservableList<Employee> employeeList = EmployeeData.getData();
    /*private static ObservableList<Employee> employeeList = FXCollections.observableArrayList(
            new Employee(1,"michel","fromage","cadre","michel.fromage@gmail.com","0625487962",12,new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
            new Employee(2,"michel","boulet","cadre","michel.fromage@gmail.com","2536145869",12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
            new Employee(3,"paula","moret","employe","paula.moret@gmail.com","1425369685",5, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
            new Employee(4,"marcel","brique","employe","marcel.brique@gmail.com","0230215456",2, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0))),
            new Employee(5,"christine","chapeau","cadre","christine.chapeau@gmail.com","5456892123",12, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0)))
    );*/

    /**
     * Returns the list of employees.
     *
     * @return an ObservableList of Employee objects
     */
    public static ObservableList<Employee> getEmployeeList() {return employeeList;}

    /**
     * Sets the list of employees.
     *
     * @param list the ObservableList of Employee objects to set
     */
    public static void setEmployeeList(ObservableList<Employee> list) {employeeList=list;}

    /**
     * Gets the next available ID for a new Employee.
     *
     * @return the next available Employee ID
     */
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

    /**
     * Adds a new Employee to the list.
     *
     * @param first the first name of the employee
     * @param last the last name of the employee
     * @param post the post of the employee
     * @param email the email of the employee
     * @param tel the telephone number of the employee
     * @param delta the delta work time of the employee
     */
    public static void addEmployee(String first,String last,String post,String email,String tel, int delta){

        Employee newEmployee = new Employee(EmployeeData.getNextId(), first, last, post, email, tel, delta, new Planning(LocalTime.of(8, 0), LocalTime.of(17, 0)));
        employeeList.add(newEmployee);
    }

    /**
     * Removes an Employee from the list.
     *
     * @param rmEmployee the Employee to remove
     */
    public static void removeEmployee(Employee rmEmployee){
        employeeList.remove(rmEmployee);

    }

    /**
     * Modifies the details of an existing Employee.
     *
     * @param employee the Employee to modify
     * @param first the new first name
     * @param last the new last name
     * @param post the new post
     * @param email the new email
     * @param tel the new telephone number
     * @param delta the new delta work time
     */
    public static void modifyEmployee(Employee employee,String first,String last,String post,String email,String tel, int delta){
        employee.setFirstName(first);
        employee.setLastName(last);
        employee.setPost(post);
        employee.setMail(email);
        employee.setTel(tel);
        employee.setDeltaWorkTime(delta);

    }

//endregion

    /**
     * Finds an Employee by their ID.
     *
     * @param employeeId the ID of the Employee to find
     * @return the Employee with the given ID, or null if not found
     */
    public static Employee findEmployeeById(String employeeId) {
        return getEmployeeList().stream()
                .filter(employee -> employee.getId() == Integer.parseInt(employeeId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the serialized file with the current list of employees.
     */
    public static void updateFile(){
        Serializer.serializeEmployees(employeeList, "SaveEmployees.ser");
    }

    /**
     * Deserializes the list of employees from the file.
     *
     * @return an ObservableList of Employee objects
     */
    public static ObservableList<Employee> getData() {
        return Deserializer.deserializeEmployes("SaveEmployees.ser");
    }

    /**
     * Changes the check-in or check-out status of an employee.
     *
     * @param employee the Employee to update
     */
    public static void changeChecked(Employee employee){
        if(employee.getCheck_in()){
            employee.setCheck_out(true);
        }
        else {
            employee.setCheck_in(true);
        }
    }

    /**
     * Updates the delta work time of an employee based on a time clocking event.
     *
     * @param timeClocking the TimeClocking event
     */
    public static void changeDelta(TimeClocking timeClocking){
        // faire l'incrémentation de la diférences
        timeClocking.getEmployee().setDeltaWorkTime(timeClocking.getEmployee().getDeltaWorkTime()/*+diférence en tranche de 15*/);
    }
}
