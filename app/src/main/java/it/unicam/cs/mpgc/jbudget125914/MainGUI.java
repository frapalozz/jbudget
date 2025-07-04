package it.unicam.cs.mpgc.jbudget125914;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainGUI extends Application {

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/newMain.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("JBudget");
        primaryStage.setScene(new javafx.scene.Scene(root));
        primaryStage.setMinHeight(450);
        primaryStage.setMinWidth(1000);
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
}
