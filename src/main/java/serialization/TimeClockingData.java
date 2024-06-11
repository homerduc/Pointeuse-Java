package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.TimeClocking;
import model.Planning;

import java.time.LocalDateTime;
import java.time.LocalTime;


public class TimeClockingData {

    //private static ObservableList<TimeClocking> timeClockingList =  Deserializer.deserializePointages("SaveTimeClockings.ser");
    private static ObservableList<TimeClocking> timeClockingList = FXCollections.observableArrayList(
            new TimeClocking(LocalDateTime.now(),EmployeeData.getEmployeeList().get(1)),
            new TimeClocking(LocalDateTime.now(),EmployeeData.getEmployeeList().get(2))
    );


    public static ObservableList<TimeClocking> getTimeClockingList() {
        return timeClockingList; }

    public static void addTimeClocking(Employee employee) {
        TimeClocking timeClocking = new TimeClocking(LocalDateTime.now(), employee);
        timeClockingList.add(timeClocking);
    }

    public static void removeTimeClocking(TimeClocking timeClocking) {
        timeClockingList.remove(timeClocking);
    }

    public static void updateFile() {
        Serializer.serializeTimeCLockings(timeClockingList, "SaveTimeClockings.ser");
    }

    public static ObservableList<TimeClocking> getData() {
        return Deserializer.deserializePointages("SaveTimeClockings.ser");
    }
}
