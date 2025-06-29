package it.unicam.cs.mpgc.jbudget125914.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    @FXML
    private AnchorPane mainAnchor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/sidebar.fxml"));
        try {
            Parent parent = loader.load();
            SidebarView sidebarView = loader.getController();

            mainAnchor.getChildren().clear();
            mainAnchor.getChildren().add(parent);
            System.out.println("Arrivato");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
