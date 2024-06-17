package serialization;

import javafx.collections.ObservableList;
import model.Employee;
import model.TimeClocking;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class provides methods to serialize Employee and TimeClocking data to files.
 */
public class Serializer {

    /**
     * Serializes a list of Employee objects to a file.
     *
     * @param employees the ObservableList of Employee objects to serialize
     * @param filename the name of the file to which the data will be serialized
     */
    public static void serializeEmployees(ObservableList<Employee> employees, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new ArrayList<Employee>(employees));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Serializes a list of TimeClocking objects to a file.
     *
     * @param timeClocking the ObservableList of TimeClocking objects to serialize
     * @param filename the name of the file to which the data will be serialized
     */
    public static void serializeTimeCLockings(ObservableList<TimeClocking> timeClocking, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(new ArrayList<TimeClocking>(timeClocking));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
