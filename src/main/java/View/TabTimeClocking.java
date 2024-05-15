package View;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class TabTimeClocking extends Tab {

    public Button bouton_history;
    public ComboBox<String> CB_TimeClocking;
    public Button bouton_Today;
    public TableColumn TableViewTimeCloking;
    public Button bouton_Checked;
    public Button bouton_unchecked;
    private String[] filters= {"checked","NoCkecked","Late","Early"};
    public void initialize(URL arg0, ResourceBundle arg1){
        CB_TimeClocking.getItems().addAll(filters);
    }

}
