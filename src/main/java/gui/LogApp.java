package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LogApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/guiLog.fxml"));

        GridPane root=loader.load();
        ControllerLog ctrl=loader.getController();
        ctrl.setStage(primaryStage);

        primaryStage.setScene(new Scene(root, 1181, 874));
        primaryStage.setTitle("Hello World");
        primaryStage.setMinHeight(874);
        primaryStage.setMinWidth(1184);
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
