package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TimeClocking implements Serializable {

    private LocalDateTime DateTime ;
    private Employee employee ;
    //private String etat; /!\

    public TimeClocking(){}

    public TimeClocking(LocalDateTime dateTime, Employee employee) {
        DateTime = dateTime;
        this.employee = employee;

//  /!\      if(employee.getCheck_in()&&!employee.getCheck_out()){
//            etat = "IN";
//        }
//        else if (employee.getCheck_in()&&employee.getCheck_out()){
//            etat = "out";
//        }
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}


