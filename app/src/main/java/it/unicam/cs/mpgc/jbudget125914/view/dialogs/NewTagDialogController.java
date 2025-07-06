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

package it.unicam.cs.mpgc.jbudget125914.view.dialogs;

import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lombok.Setter;

/**
 * This class is the controller for NewTagDialog.fxml view
 */
public class NewTagDialogController extends BaseDialog {

    @FXML
    private TextField name;

    @Setter
    private FinancialCategory category;
    @Setter
    private FinancialGroup group;
    @Setter
    private ChoiceBox<FinancialCategory> categoryChoice;

    @Setter
    Runnable action;

    /**
     * Create new Tag
     */
    @FXML
    public void apply() {
        if(sanityCheck())
            return;
        FinancialTag tag = new FinancialTag(name.getText(), category);
        tag.setGroupId(group);
        categoryChoice.setValue(null);
        getService().getDaoManager().getTagDAO().create(tag);

        getService().getFetchManager().updateGroup(getService().getDaoManager(), getService().getFilterManager());
        getService().update();
        action.run();
        getStage().close();
        alertBuilder("Now select the tags");
    }

    private boolean sanityCheck() {
        if(name.getText().isEmpty()) {
            alertBuilder("Name not valid");
            return true;
        }
        return false;
    }

}
