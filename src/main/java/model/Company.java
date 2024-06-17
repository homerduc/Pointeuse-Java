package model;

import java.util.ArrayList;


/**
 * This class represents a Company with a name, address, SIRET number, and a list of departments.
 */

public class Company {
    private String Name ;
    private String Address;
    private int Siret ;
    private ArrayList<Department> Departments ;

    /**
     * Default constructor that initializes the list of departments.
     */
    public Company() {
        Departments = new ArrayList<Department>();
    }
    /**
     * Gets the name of the company.
     *
     * @return the name of the company
     */

    public String getName() {
        return Name;
    }

    /**
     * Sets the name of the company.
     *
     * @param name the new name of the company
     */

    public void setName(String name) {
        Name = name;
    }
    /**
     * Gets the address of the company.
     *
     * @return the address of the company
     */

    public String getAddress() {
        return Address;
    }
    /**
     * Sets the address of the company.
     *
     * @param address the new address of the company
     */

    public void setAddress(String address) {
        Address = address;
    }
    /**
     * Gets the SIRET number of the company.
     *
     * @return the SIRET number of the company
     */

    public int getSiret() {
        return Siret;
    }
    /**
     * Sets the SIRET number of the company.
     *
     * @param siret the new SIRET number of the company
     */

    public void setSiret(int siret) {
        Siret = siret;
    }
    /**
     * Gets the list of departments in the company.
     *
     * @return the list of departments in the company
     */

    public ArrayList<Department> getDepartments() {
        return Departments;
    }
    /**
     * Sets the list of departments in the company.
     *
     * @param departments the new list of departments in the company
     */

    public void setDepartments(ArrayList<Department> departments) {
        Departments = departments;
    }

    /**
     * Constructor that initializes the company with the given name, SIRET number, address, and list of departments.
     *
     * @param name the name of the company
     * @param siret the SIRET number of the company
     * @param address the address of the company
     * @param departments the list of departments in the company
     */

    public Company(String name, int siret, String address, ArrayList<Department> departments) {
        Name = name;
        Siret = siret;
        Address = address;
        Departments = departments;
    }
}
