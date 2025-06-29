package it.unicam.cs.mpgc.jbudget125914;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUI extends Application {

    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/COMPONENTS/main.fxml"));
        primaryStage.setTitle("JBudget");
        primaryStage.setScene(new javafx.scene.Scene(root));
        primaryStage.setMaxWidth(500);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
