package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReportController {
    @FXML
    Label informationLabel;

    String information;

    public void setInformation(String information){
        this.informationLabel.setText(information);
    }

    @FXML
    void initialize() {

    }
}
