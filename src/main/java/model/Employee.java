package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Employee {

    private int Id ;
    private String FirstName;
    private String LastName ;
    private String Post ;
    private String Mail ;
    private String Tel ;
    private int DeltaWorkTime ;
    private Planning planning ;
    private ArrayList<TimeClocking> ListClocking ;

    public Employee(int id, String firstName, String lastName, String post, String mail, String tel ,int DeltaWorkTime) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Post = post;
        Mail = mail;
        Tel = tel;
        LocalTime DateD =LocalTime.of(8,0);
        LocalTime DateF =LocalTime.of(17,0);
        this.DeltaWorkTime = 0;
        this.planning = new Planning(DateD,DateF);
        ListClocking =new ArrayList<TimeClocking>();
    }


    public void setId(int id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setPost(String post) {
        Post = post;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public void setDeltaWorkTime(int deltaWorkTime) {
        DeltaWorkTime = deltaWorkTime;
    }

    public void setPlanning(Planning planning) {
        this.planning = planning;
    }

    public void setListClocking(ArrayList<TimeClocking> listClocking) {
        ListClocking = listClocking;
    }


    public String getMail() {
        return Mail;
    }

    public String getTel() {
        return Tel;
    }


    public int getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPost() {
        return Post;
    }

    public int getDeltaWorkTime() {
        return DeltaWorkTime;
    }

    public Planning getPlanning() {
        return planning;
    }

    //  public ArrayList<TimeClocking> getListClocking() {
    //  return ListClocking;
    // }

    @Override
    public String toString() {
        return "Employee{" +
                "Id=" + Id +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Post='" + Post + '\'' +
                ", Mail='" + Mail + '\'' +
                ", Tel='" + Tel + '\'' +
                '}';
    }

}
