package serialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import model.TimeClocking;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods to deserialize Employee and TimeClocking objects from files.
 */
public class Deserializer {

    /**
     * Deserializes a list of Employee objects from a specified file.
     *
     * @param filename the name of the file from which to deserialize the Employee objects
     * @return an ObservableList of Employee objects
     */
    @SuppressWarnings("unchecked")
    public static ObservableList<Employee> deserializeEmployes(String filename) {
        ObservableList<Employee> employees = null;
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<Employee> list = (List<Employee>) ois.readObject();
            employees = FXCollections.observableList(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    /**
     * Deserializes a list of TimeClocking objects from a specified file.
     *
     * @param filename the name of the file from which to deserialize the TimeClocking objects
     * @return an ObservableList of TimeClocking objects
     */
    @SuppressWarnings("unchecked")
    public static ObservableList<TimeClocking> deserializePointages(String filename) {
        ObservableList<TimeClocking> timeClockings = null;
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<TimeClocking> list = (List<TimeClocking>) ois.readObject();
            timeClockings = FXCollections.observableList(list);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return timeClockings;
    }
}
