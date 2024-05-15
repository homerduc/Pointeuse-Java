package Model;

import java.util.ArrayList;

public class Company {
    private String Name ;
    private String Address;
    private int Siret ;
    private ArrayList<Department> Departments ;

    public Company() {
        Departments = new ArrayList<Department>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getSiret() {
        return Siret;
    }

    public void setSiret(int siret) {
        Siret = siret;
    }

    public ArrayList<Department> getDepartments() {
        return Departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        Departments = departments;
    }

    public Company(String name, int siret, String address, ArrayList<Department> departments) {
        Name = name;
        Siret = siret;
        Address = address;
        Departments = departments;
    }
}
