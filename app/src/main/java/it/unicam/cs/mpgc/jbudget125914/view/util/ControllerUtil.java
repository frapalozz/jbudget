package it.unicam.cs.mpgc.jbudget125914.view.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class ControllerUtil {

    public void formatAmount(TextField amount) {
        amount.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if(newText.matches("-?\\d*\\.?\\d*"))
                return change;
            return null;
        }));
    }

    public void dialogBuilder(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/"+path+".fxml"));

            Parent root = loader.load();
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle(title);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
