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

import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.view.BaseController;
import it.unicam.cs.mpgc.jbudget125914.view.util.ControllerUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Base transaction controller dialog
 * @param <T> transaction type
 */
@Getter
public abstract class BaseTransactionController<
        T extends Transaction<FinancialAmount,LocalDate,FinancialTag,FinancialAccount>
        > extends BaseController implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private TextField amount;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<FinancialAccount> account;
    @FXML
    private ChoiceBox<FinancialCategory> category;
    @FXML
    private VBox tagBox;
    @FXML
    private Button applyButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.formatAmount(getAmount());
        getAccount().getItems().addAll(getService().getFilterManager().getGroup().getAccounts());
        getCategory().getItems().addAll(getService().getFilterManager().getGroup().getCategories());

        getCategory().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
                setCategory(newValue)
        );
    }

    /**
     * Apply changes
     */
    @FXML
    private void apply() {
        if(getApplyButton().getText().equals("Create")) {
            applyCreate();
        } else {
            applyUpdate();
        }
    }

    /**
     * Create the new Transaction
     */
    protected abstract void applyCreate();

    /**
     * Update the Transaction
     */
    protected abstract void applyUpdate();

    /**
     * Populate category ChoiceBox
     */
    @FXML
    public void populateCategory() {
        FinancialCategory category = getCategory().getValue();
        getCategory().getItems().clear();
        if(category != null)
            getCategory().setValue(category);
        getCategory().getItems().addAll(getService().getFilterManager().getGroup().getCategories());
    }

    /**
     * Populate account ChoiceBox
     */
    @FXML
    public void populateAccounts() {
        FinancialAccount account = getAccount().getValue();
        getAccount().getItems().clear();
        if(account != null)
            getAccount().setValue(account);
        getAccount().getItems().addAll(getService().getFilterManager().getGroup().getAccounts());
    }

    /**
     * Open new account dialog
     */
    @FXML
    public void openNewAccountDialog() {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder("dialogs/NewAccountDialog","New Account");
    }

    /**
     * Open new category dialog
     */
    @FXML
    public void openNewCategoryDialog() {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder("dialogs/NewCategoryDialog","New Category");
    }

    /**
     * open new tag dialog
     */
    @FXML
    public void openNewTagDialog() {
        if(getCategory().getValue() == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/dialogs/NewTagDialog.fxml"));

            Parent root = loader.load();
            NewTagDialogController controller = loader.getController();
            controller.setCategory(getCategory().getValue());
            controller.setAction(this::updateTags);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("New Tag");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Apply the changes to the Transaction
     * @param transaction the Transaction to change
     */
    protected void applyChanges(T transaction) {
        transaction.setDate(date.getValue());
        transaction.setDescription(description.getText());
        transaction.setAccount(account.getValue());
        transaction.setTags(getTags());
        transaction.setAmount(new FinancialAmount(BigDecimal.valueOf(Double.parseDouble(amount.getText()))));
    }

    private Set<FinancialTag> getTags() {
        Set<FinancialTag> tags = new HashSet<>();

        getTagBox().getChildren().forEach(tag -> {
            if(tag instanceof CheckBox checkBox) {
                if(checkBox.isSelected()) {
                    getService().getFilterManager().getGroup().getTags().forEach(t -> {
                        if(t.getName().equals(checkBox.getText())) {
                            tags.add(t);
                        }
                    });
                }
            }
        });

        return tags;
    }

    /**
     * Set the applyButton text
     * @param text the new text
     */
    public void setApplyButton(String text) {
        getApplyButton().setText(text);
    }

    /**
     * Set the new description for the Transaction
     * @param description the new description
     */
    public void setDescription(String description) {
        getDescription().setText(description);
    }

    /**
     * Set the amount for the Transaction
     * @param amount the new amount
     */
    public void setAmount(String amount) {
        getAmount().setText(amount);
    }

    /**
     * Set the date for the Transaction
     * @param date the new date
     */
    public void setDate(LocalDate date) {
        getDate().setValue(date);
    }

    /**
     * Set the account for the Transaction
     * @param account the new account
     */
    public void setAccount(FinancialAccount account) {
        getAccount().setValue(account);
    }

    /**
     * Set the category for the Transaction
     * @param category the new category
     */
    public void setCategory(FinancialCategory category) {
        getCategory().setValue(category);
        updateTags();
    }

    private void updateTags() {
        getTagBox().getChildren().clear();
        getTagBox().getChildren().addAll(generateTags(getCategory().getValue()));
    }

    /**
     * Set the tags of the Transaction
     * Used when modifying a Transaction
     * @param tags tags to set active
     */
    public void setActiveTags(Set<FinancialTag> tags) {
        tags.forEach(t ->
                getTagBox().getChildren().forEach(tag -> {
                    if(tag instanceof CheckBox checkBox) {
                        if(checkBox.getText().equals(t.getName()))
                            checkBox.setSelected(true);
                    }
                })
        );
    }

    private List<CheckBox> generateTags(FinancialCategory category) {
        List<CheckBox> tags = new ArrayList<>();

        getService().getFilterManager().getGroup().getTags()
                .stream()
                .filter(t -> t.getCategory().equals(category))
                .forEach(t -> tags.add(generateTag(t.getName())));

        return tags;
    }

    private CheckBox generateTag(String tagName) {
        CheckBox tag = new CheckBox();
        tag.setText(tagName);
        return tag;
    }

}
