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
import it.unicam.cs.mpgc.jbudget125914.models.entities.group.FinancialGroup;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.view.util.ControllerUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

/**
 * Base new transaction controller
 * @param <T> Transaction type
 */
@Getter
public abstract class BaseNewTransactionController<T extends Transaction<FinancialAmount, LocalDate,FinancialTag,FinancialAccount>>  extends BaseDialog implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private TextField amount;
    @FXML
    private ChoiceBox<FinancialAccount> account;
    @FXML
    private ChoiceBox<FinancialCategory> category;
    @FXML
    private VBox tagBox;

    private FinancialGroup group;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        group = getService().getFilterManager().getGroup();
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.formatAmount(getAmount());
        if(getService().getFilterManager().getGroup().getAccounts() != null && !getService().getFilterManager().getGroup().getAccounts().isEmpty()) {
            populateAccounts();
        }
        if(getService().getFilterManager().getGroup().getCategories() != null && !getService().getFilterManager().getGroup().getCategories().isEmpty()) {
            populateCategory();
        }


        getCategory().getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->
                setCategory(newValue)
        );
    }

    /**
     * Open new account dialog
     */
    @FXML
    public void openNewAccountDialog() {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder("dialogs/NewAccountDialog","New Account");
        populateAccounts();
        group = getService().getFilterManager().getGroup();
    }

    /**
     * Open new category dialog
     */
    @FXML
    public void openNewCategoryDialog() {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder("dialogs/NewCategoryDialog","New Category");
        populateCategory();
        group = getService().getFilterManager().getGroup();
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
            controller.setCategoryChoice(getCategory());
            controller.setGroup(group);
            controller.setAction(this::updateTags);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("New Tag");
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CLOSE);
            dialog.getDialogPane().setContent(root);
            controller.setStage((Stage) dialog.getDialogPane().getScene().getWindow());

            dialog.showAndWait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    private void populateCategory() {
        FinancialCategory category = getCategory().getValue();
        getCategory().getItems().clear();
        if(category != null)
            getCategory().setValue(category);
        getCategory().getItems().addAll(getService().getFilterManager().getGroup().getCategories());
    }

    private void populateAccounts() {
        FinancialAccount account = getAccount().getValue();
        getAccount().getItems().clear();
        if(account != null)
            getAccount().setValue(account);
        getAccount().getItems().addAll(getService().getFilterManager().getGroup().getAccounts());
    }

    /**
     * Apply the changes to the Transaction
     * @param transaction the Transaction to change
     */
    protected boolean applyChanges(T transaction) {
        Set<FinancialTag> tags = getTags();
        if(sanityCheck(tags)) {
            return false;
        }
        transaction.setDescription(getDescription().getText());
        transaction.setAmount(new FinancialAmount(BigDecimal.valueOf(Double.parseDouble(getAmount().getText()))));
        transaction.setAccount(getAccount().getValue());
        transaction.setTags(tags);
        return true;
    }

    protected boolean sanityCheck(Set<FinancialTag> tags) {
        if(getDescription().getText().isEmpty()) {
            alertBuilder("Description not valid");
            return true;
        }
        if(getAmount().getText().isEmpty() || getAmount().getText().equals("-")) {
            alertBuilder("Amount not valid");
            return true;
        }
        if(BigDecimal.valueOf(Double.parseDouble(getAmount().getText())).equals(BigDecimal.ZERO)) {
            alertBuilder("Amount not valid");
            return true;
        }
        if(getAccount().getValue() == null) {
            alertBuilder("Account not valid");
            return true;
        }
        if(tags.isEmpty()) {
            alertBuilder("Insert a tag");
            return true;
        }
        return false;
    }

    protected void updateTags() {
        getTagBox().getChildren().clear();
        getTagBox().getChildren().addAll(generateTags(getCategory().getValue()));
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

    protected Set<FinancialTag> getTags() {
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
}
