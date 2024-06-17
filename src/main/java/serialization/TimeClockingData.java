package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TimeClocking;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class provides methods to manage and serialize a list of TimeClocking objects.
 */
public abstract class TimeClockingData {

    //private static ObservableList<TimeClocking> timeClockingList = Deserializer.deserializePointages("SaveTimeClockings.ser");
    private static ObservableList<TimeClocking> timeClockingList = TimeClockingData.getTimeClockingList();

    /**
     * Returns the list of TimeClocking objects.
     *
     * @return the ObservableList of TimeClocking objects
     */
    public static ObservableList<TimeClocking> getTimeClockingList() {
        return timeClockingList;
    }

    /**
     * Adds a TimeClocking object to the list.
     *
     * @param timeClocking the TimeClocking object to add
     */
    public static void addTimeClocking(TimeClocking timeClocking) {
        if (timeClockingList == null) {
            timeClockingList = FXCollections.observableArrayList();
        }
        timeClockingList.add(timeClocking);
    }

    /**
     * Removes a TimeClocking object from the list.
     *
     * @param timeClocking the TimeClocking object to remove
     */
    public static void removeTimeClocking(TimeClocking timeClocking) {
        timeClockingList.remove(timeClocking);
    }

    /**
     * Updates the file with the current list of TimeClocking objects.
     */
    public static void updateFile() {
        Serializer.serializeTimeCLockings(timeClockingList, "SaveTimeClockings.ser");
    }

    /**
     * Updates the list of TimeClocking objects with data from the file.
     */
    public static void updateData() {
        timeClockingList = getData();
    }

    /**
     * Returns the list of TimeClocking objects from the file.
     *
     * @return the ObservableList of TimeClocking objects
     */
    public static ObservableList<TimeClocking> getData() {
        return Deserializer.deserializePointages("SaveTimeClockings.ser");
    }
}
