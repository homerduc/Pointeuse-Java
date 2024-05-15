package Model;

import java.util.ArrayList;

public class Department {

    private int Id_Deprt ;
    private String Name ;
    private ArrayList<Employee> Employees ;

    public Department(){
        Employees =new ArrayList<Employee>() ;
    }
    public int getId_Deprt() {
        return Id_Deprt;
    }

    public void setId_Deprt(int id_Deprt) {
        Id_Deprt = id_Deprt;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Employee> getEmployees() {
        return Employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        Employees = employees;
    }

    public Department(int id_Deprt, String name) {
        Id_Deprt = id_Deprt;
        Name = name;
    }
}
