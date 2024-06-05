package model;

import javafx.beans.binding.BooleanExpression;
import javafx.scene.control.CheckBox;

import java.io.Serializable;
import java.time.LocalTime;

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

    public Employee(int id, String firstName, String lastName, String post, String mail, String tel , int DeltaWorkTime, Planning planning) {
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
        Check_in = false;
        Check_out = false;
//        this.Check_in = new CheckBox();
//        this.Check_in.setSelected(false);
//        this.Check_out= new CheckBox();
//        this.Check_out.setSelected(false);
//        this.Check_out.setDisable(true);

//        this.Check_in.selectedProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue) {
//                Check_out.setDisable(false);
//            } else {
//                Check_out.setDisable(true);
//                Check_out.setSelected(false);
//            }
//        });
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
    public void setCheck_in(Boolean config) {Check_in = config;}
    public void setCheck_out(Boolean config) {Check_out = config;}

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
    public Boolean getCheck_in() {return Check_in;}
    public Boolean getCheck_out() {return Check_out;}

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
