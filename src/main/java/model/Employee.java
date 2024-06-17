package model;

import javafx.beans.binding.BooleanExpression;
import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * This class represents an Employee with various attributes like ID, name, post, contact details, work time delta, and planning.
 */
public class Employee implements Serializable {
    private int Id ;
    private String FirstName;
    private String LastName ;
    private String Post ;
    private String Mail ;
    private String Tel ;
    private int DeltaWorkTime ;
    private Planning planning ;
    private Boolean Check_in;
    private Boolean Check_out;

    /**
     * Constructs an Employee with the specified details.
     *
     * @param id the ID of the employee
     * @param firstName the first name of the employee
     * @param lastName the last name of the employee
     * @param post the post/position of the employee
     * @param mail the email of the employee
     * @param tel the telephone number of the employee
     * @param delta the delta work time of the employee
     * @param planning the planning details of the employee
     */
    public Employee(int id, String firstName, String lastName, String post, String mail, String tel , int delta, Planning planning) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Post = post;
        Mail = mail;
        Tel = tel;
        LocalTime DateD =LocalTime.of(8,0);
        LocalTime DateF =LocalTime.of(17,0);
        DeltaWorkTime = delta;
        this.planning = new Planning(DateD,DateF);
        Check_in = false;
        Check_out = false;
    }

    /**
     * Sets the ID of the employee.
     *
     * @param id the new ID of the employee
     */
    public void setId(int id) {
        Id = id;
    }

    /**
     * Sets the first name of the employee.
     *
     * @param firstName the new first name of the employee
     */
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    /**
     * Sets the post/position of the employee.
     *
     * @param post the new post/position of the employee
     */
    public void setPost(String post) {
        Post = post;
    }

    /**
     * Sets the last name of the employee.
     *
     * @param lastName the new last name of the employee
     */
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /**
     * Sets the email of the employee.
     *
     * @param mail the new email of the employee
     */
    public void setMail(String mail) {
        Mail = mail;
    }

    /**
     * Sets the telephone number of the employee.
     *
     * @param tel the new telephone number of the employee
     */
    public void setTel(String tel) {
        Tel = tel;
    }

    /**
     * Sets the delta work time of the employee.
     *
     * @param deltaWorkTime the new delta work time of the employee
     */
    public void setDeltaWorkTime(int deltaWorkTime) {
        DeltaWorkTime = deltaWorkTime;
    }

    /**
     * Sets the planning details of the employee.
     *
     * @param planning the new planning details of the employee
     */
    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    /**
     * Sets the check-in status of the employee.
     *
     * @param config the new check-in status of the employee
     */
    public void setCheck_in(Boolean config) {Check_in = config;}

    /**
     * Sets the check-out status of the employee.
     *
     * @param config the new check-out status of the employee
     */
    public void setCheck_out(Boolean config) {Check_out = config;}

    /**
     * Gets the email of the employee.
     *
     * @return the email of the employee
     */
    public String getMail() {
        return Mail;
    }

    /**
     * Gets the telephone number of the employee.
     *
     * @return the telephone number of the employee
     */
    public String getTel() {
        return Tel;
    }

    /**
     * Gets the ID of the employee.
     *
     * @return the ID of the employee
     */
    public int getId() {
        return Id;
    }

    /**
     * Gets the first name of the employee.
     *
     * @return the first name of the employee
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * Gets the last name of the employee.
     *
     * @return the last name of the employee
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * Gets the post/position of the employee.
     *
     * @return the post/position of the employee
     */
    public String getPost() {
        return Post;
    }

    /**
     * Gets the delta work time of the employee.
     *
     * @return the delta work time of the employee
     */
    public int getDeltaWorkTime() {
        return DeltaWorkTime;
    }

    /**
     * Gets the planning details of the employee.
     *
     * @return the planning details of the employee
     */
    public Planning getPlanning() {
        return planning;
    }

    /**
     * Gets the check-in status of the employee.
     *
     * @return the check-in status of the employee
     */
    public Boolean getCheck_in() {return Check_in;}

    /**
     * Gets the check-out status of the employee.
     *
     * @return the check-out status of the employee
     */
    public Boolean getCheck_out() {return Check_out;}

    /**
     * Returns a string representation of the employee.
     *
     * @return a string representation of the employee
     */
    @Override
    public String toString() {
        System.out.println("Employee{\n" +
                "Id= " + getId() +
                ", FirstName= " + getFirstName() +
                ", LastName= " + getLastName() +
                ", \nPost= " + getPost() +
                ", Mail= " + getMail() +
                ", Tel= " + getTel() +
                ", \nCheck_in= " + getCheck_in()+
                ", \nCheck_out= "+ getCheck_out() +
                '}');
        return null;
    }

}
