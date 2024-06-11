package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TimeClocking;

import java.util.ArrayList;
import java.util.List;

public abstract class TimeClockingData {

    private static ObservableList<TimeClocking> timeClockingList = FXCollections.observableArrayList();

    public static ObservableList<TimeClocking> getTimeClockingList() {
        return timeClockingList;
    }

    public static void addTimeClocking(TimeClocking timeClocking) {
        if (timeClockingList == null) {
            timeClockingList = FXCollections.observableArrayList();
        }
        timeClockingList.add(timeClocking);
    }


    public static void removeTimeClocking(TimeClocking timeClocking) {
        timeClockingList.remove(timeClocking);
    }

    public static void updateFile() {
        Serializer.serializeTimeCLockings(timeClockingList, "SaveTimeClockings.ser");
    }

    public static void getData() {
        Deserializer.deserializePointages("SaveTimeClockings.ser");
    }
}
