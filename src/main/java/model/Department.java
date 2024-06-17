package model;

import java.util.ArrayList;

/**
 * This class represents a Department with an ID, name, and a list of employees.
 */
public class Department {

    private int Id_Deprt ;
    private String Name ;
    private ArrayList<Employee> Employees ;

    /**
     * Default constructor that initializes the list of employees.
     */
    public Department(){
        Employees =new ArrayList<Employee>() ;
    }

    /**
     * Gets the ID of the department.
     *
     * @return the ID of the department
     */
    public int getId_Deprt() {
        return Id_Deprt;
    }

    /**
     * Sets the ID of the department.
     *
     * @param id_Deprt the new ID of the department
     */
    public void setId_Deprt(int id_Deprt) {
        Id_Deprt = id_Deprt;
    }

    /**
     * Gets the name of the department.
     *
     * @return the name of the department
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the name of the department.
     *
     * @param name the new name of the department
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Gets the list of employees in the department.
     *
     * @return the list of employees in the department
     */
    public ArrayList<Employee> getEmployees() {
        return Employees;
    }

    /**
     * Sets the list of employees in the department.
     *
     * @param employees the new list of employees in the department
     */
    public void setEmployees(ArrayList<Employee> employees) {
        Employees = employees;
    }

    /**
     * Constructor that initializes the department with the given ID and name.
     *
     * @param id_Deprt the ID of the department
     * @param name the name of the department
     */
    public Department(int id_Deprt, String name) {
        Id_Deprt = id_Deprt;
        Name = name;
    }
}
