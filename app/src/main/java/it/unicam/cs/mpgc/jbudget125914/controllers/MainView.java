package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.ServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    @FXML
    private HBox mainAnchor;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/sidebar.fxml"));
        FXMLLoader infoBlock = new FXMLLoader(getClass().getResource("/COMPONENTS/infoBlock.fxml"));
        try {
            Parent parent = loader.load();
            Parent infoBlockParent = infoBlock.load();

            HBox.setHgrow(infoBlockParent, Priority.ALWAYS);

            Separator separator = new Separator();
            separator.setOrientation(Orientation.VERTICAL);
            separator.setMaxWidth(0.5);
            separator.setPrefWidth(0.5);
            separator.setOpacity(0.5);

            mainAnchor.getChildren().clear();
            mainAnchor.getChildren().addAll(parent, separator, infoBlockParent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
