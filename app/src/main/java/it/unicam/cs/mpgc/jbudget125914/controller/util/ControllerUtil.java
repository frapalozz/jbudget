/**
 * MIT License
 * Copyright (c) 2025 Francesco Palozzi
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package it.unicam.cs.mpgc.jbudget125914.controller.util;

import it.unicam.cs.mpgc.jbudget125914.controller.dialogs.BaseDialog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Stage;

/**
 * Controller utilities class
 */
public class ControllerUtil {

    /**
     * Format TextField for number
     * @param amount TextField to format
     */
    public void formatAmount(TextField amount) {
        amount.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if(newText.matches("-?\\d*\\.?\\d*"))
                return change;
            return null;
        }));
    }

    /**
     * Build dialog
     * @param path fxml path
     * @param title dialog title
     */
    public void dialogBuilder(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/"+path+".fxml"));

            Parent root = loader.load();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle(title);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);
            BaseDialog controller = loader.getController();
            controller.setStage((Stage) dialog.getDialogPane().getScene().getWindow());

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
