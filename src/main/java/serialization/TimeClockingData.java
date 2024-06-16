package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.TimeClocking;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class TimeClockingData {

    //private static ObservableList<TimeClocking> timeClockingList = Deserializer.deserializePointages("SaveTimeClockings.ser");
    private static ObservableList<TimeClocking> timeClockingList = TimeClockingData.getTimeClockingList();

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

    // Met à jour le fichier avec la liste de la classe
    public static void updateFile() {
        Serializer.serializeTimeCLockings(timeClockingList, "SaveTimeClockings.ser");
    }

    // Met à jour la liste de la classe avec celle du fichier
    public static void updateData() {
        timeClockingList = getData();
    }

    // Renvoie la liste du fichier
    public static ObservableList<TimeClocking> getData() {
        return Deserializer.deserializePointages("SaveTimeClockings.ser");
    }
}
