package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;

/**
 * This class represents a time clocking event for an employee.
 */
public class TimeClocking implements Serializable {

    private LocalDateTime DateTime ;
    private Employee employee ;
    //private String etat; /!\
    //private Float delta; /!\

    /**
     * Constructs a new TimeClocking instance with default values.
     */
    public TimeClocking(){}

    /**
     * Constructs a new TimeClocking instance with specified date and time, and associated employee.
     *
     * @param dateTime the date and time of the clocking event
     * @param employee the employee associated with the clocking event
     */
    public TimeClocking(LocalDateTime dateTime, Employee employee) {
        DateTime = dateTime;
        this.employee = employee;
    }

    /**
     * Gets the date and time of the clocking event.
     *
     * @return the date and time
     */
    public LocalDateTime getDateTime() {
        return DateTime;
    }

    /**
     * Sets the date and time of the clocking event.
     *
     * @param dateTime the new date and time
     */
    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    /**
     * Gets the employee associated with the clocking event.
     *
     * @return the employee
     */
    public Employee getEmployee() {return employee;}

    /**
     * Sets the employee associated with the clocking event.
     *
     * @param employee the new employee
     */
    public void setEmployee(Employee employee) {this.employee = employee;}


//    public void setDelta(float config){delta = config;}
//    public float getDelta(){return delta;}

}


