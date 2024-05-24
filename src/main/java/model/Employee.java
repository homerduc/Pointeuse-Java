package model;

import javafx.scene.control.CheckBox;
import view.TabTimeClocking;

import java.time.LocalTime;

public class Employee {
    private int Id ;
    private String FirstName;
    private String LastName ;
    private String Post ;
    private String Mail ;
    private String Tel ;
    private int DeltaWorkTime ;
    private Planning planning ;
    private CheckBox Check_in;
    private CheckBox Check_out;

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
        this.Check_in = new CheckBox();
        this.Check_in.setSelected(false);
        this.Check_out= new CheckBox();
        this.Check_out.setSelected(false);
        this.Check_out.setDisable(true);

        this.Check_in.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                Check_out.setDisable(false);
            } else {
                Check_out.setDisable(true);
                Check_out.setSelected(false);
            }
        });
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
    public void setCheck_in(Boolean config) {Check_in.setSelected(config);}
    public void setCheck_out(Boolean config) {Check_out.setSelected(config);}

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
    public CheckBox getCheck_in() {return Check_in;}
    public CheckBox getCheck_out() {return Check_out;}

    @Override
    public String toString() {
        System.out.println("Employee{\n" +
                "Id= " + Id +
                ", FirstName= " + FirstName +
                ", LastName= " + LastName +
                ", \nPost= " + Post +
                ", Mail= " + Mail +
                ", Tel= " + Tel +
                ", \nCheck_in= " + Check_in.isSelected() +
                ", \nCheck_out= " + Check_out.isSelected() +
                '}');
        return null;
    }


}
