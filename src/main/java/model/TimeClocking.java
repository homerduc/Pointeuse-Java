package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

public class TimeClocking implements Serializable {

    private LocalDateTime DateTime ;
    private Employee employee ;
    //private String etat; /!\
    //private Float delta; /!\

    public TimeClocking(){}

    public TimeClocking(LocalDateTime dateTime, Employee employee) {
        DateTime = dateTime;
        this.employee = employee;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public Employee getEmployee() {return employee;}

    public void setEmployee(Employee employee) {this.employee = employee;}


//    public void setDelta(float config){delta = config;}
//    public float getDelta(){return delta;}

}


