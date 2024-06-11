package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.TimeClocking;

import java.time.LocalDateTime;


public class TimeClockingData {
    private static ObservableList<TimeClocking> timeClockingList = Deserializer.deserializePointages("SaveTimeClockings.ser");

    public static ObservableList<TimeClocking> getTimeClockingList() { return timeClockingList; }

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
