package serialization;

import javafx.collections.ObservableList;
import model.Employee;
import model.TimeClocking;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Serializer {
    public static void serializeEmployees(ObservableList<Employee> employees, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeTimeCLockings(ObservableList<TimeClocking> timeClocking, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(timeClocking);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
