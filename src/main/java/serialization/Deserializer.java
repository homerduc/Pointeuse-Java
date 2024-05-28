package serialization;

import javafx.collections.ObservableList;
import model.Employee;
import model.TimeClocking;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Deserializer {

    @SuppressWarnings("unchecked")
    public static ObservableList<Employee> deserializeEmployes(String filename) {
        ObservableList<Employee> employees = null;
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            employees = (ObservableList<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @SuppressWarnings("unchecked")
    public static ObservableList<TimeClocking> deserializePointages(String filename) {
        ObservableList<TimeClocking> timeClockings = null;
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            timeClockings = (ObservableList<TimeClocking>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return timeClockings;
    }
}
