package Model;

import java.time.LocalDateTime;

public class TimeClocking {

    private LocalDateTime DateTime ;
    private Employee employee ;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    //public void timechekingestime (local DateTime dateTime)

}


