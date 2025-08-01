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

package it.unicam.cs.mpgc.jbudget125914.controller.dialogs;

import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller for NewGroupDialog.fxml view
 */
public class NewGroupDialogController extends BaseDialog implements Initializable {

    @FXML
    private TextField groupName;

    @FXML
    private ChoiceBox<String> currency;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currency.getItems().addAll("$", "€", "£");
    }

    /**
     * Create group
     */
    @FXML
    public void apply() {
        if(sanityCheck())
            return;
        FinancialGroup group = new FinancialGroup(this.groupName.getText(), this.currency.getValue());

        getService().getFilterManager().setGroup(getService().getRepositoryManager().getGroupRepository().create(group));
        getService().update();
        getStage().close();
    }

    private boolean sanityCheck() {
        if(groupName.getText().isEmpty()) {
            alertBuilder("Group name not valid");
            return true;
        }
        if(currency.getValue() == null || currency.getValue().isEmpty()) {
            alertBuilder("Currency not valid");
            return true;
        }
        return false;
    }
}
