package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLog {

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signInButton;

    @FXML
    private Button cancelButton;
    private Stage stage;

    @FXML
    void handleCancelButton(ActionEvent event) {
        this.stage.close();
    }

    @FXML
    void handleSignInButton(ActionEvent event) {
        String user = userTextField.getText();
        String password = passwordTextField.getText();

        if (user.equals("admin") && password.equals("admin"))
        {
            try {
                AdminApp adminApp = new AdminApp();
                this.stage.close();
                adminApp.start(new Stage());
            } catch (Exception e) {
                MessageAlert.showErrorMessage(null, e.getMessage());
            }
        }
        else if (user.equals("student") && password.equals("student")){
            try {
                StudentApp studentApp = new StudentApp();
                this.stage.close();
                studentApp.start(new Stage());
            } catch (Exception e) {
                MessageAlert.showErrorMessage(null, e.getMessage());
            }
        }
        else if (user.equals("teacher") && password.equals("teacher"))
           {
               try {
                   TeacherApp teacherApp = new TeacherApp();
                   this.stage.close();
                   teacherApp.start(new Stage());
               } catch (Exception e) {
                   MessageAlert.showErrorMessage(null, e.getMessage());
               }
           }
        else
            MessageAlert.showErrorMessage(null,"Incorect username or password!");
    }


    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void initialize() {
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'guiLog.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'guiLog.fxml'.";

    }
}
