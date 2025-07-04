package it.unicam.cs.mpgc.jbudget125914.controllers;

import it.unicam.cs.mpgc.jbudget125914.controllers.util.ControllerUtil;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.category.FinancialCategory;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.models.services.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class TransactionDialogController implements Initializable {

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
    private VBox tags;
    @FXML
    private Button applyButton;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.formatAmount(amount);
        account.getItems().addAll(service.getFilterManager().getGroup().getAccounts());
        category.getItems().addAll(service.getFilterManager().getGroup().getCategories());

        category.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            setCategory(newValue);
        });
    }

    @FXML
    public void populateAccounts() {
        FinancialAccount account = this.account.getValue();
        this.account.getItems().clear();
        if(account != null)
            this.account.setValue(account);
        this.account.getItems().addAll(service.getFilterManager().getGroup().getAccounts());
    }

    @FXML
    public void populateCategory() {
        FinancialCategory category = this.category.getValue();
        this.category.getItems().clear();
        if(category != null)
            this.category.setValue(category);
        this.category.getItems().addAll(service.getFilterManager().getGroup().getCategories());
    }

    @FXML
    private void apply() {
        if(applyButton.getText().equals("Create")) {
            applyCreate();
        } else {
            applyUpdate();
        }
    }

    private void applyCreate() {
        FinancialTransaction transaction = new FinancialTransaction(
                new FinancialAmount(BigDecimal.valueOf(Double.parseDouble(amount.getText()))),
                date.getValue(),
                description.getText(),
                account.getValue(),
                getTags()
        );

        service.getGeneralManager().getTransactionService().create(transaction);
        service.update();
    }

    private void applyUpdate() {
        FinancialTransaction transaction = service.getFilterManager().getTransaction();
        transaction.setDate(date.getValue());
        transaction.setDescription(description.getText());
        transaction.setAccount(account.getValue());
        transaction.setTags(getTags());
        transaction.setAmount(new FinancialAmount(BigDecimal.valueOf(Double.parseDouble(amount.getText()))));
        service.getGeneralManager().getTransactionService().update(transaction);
        service.update();
        service.getFilterManager().setTransaction(transaction);
    }

    private Set<FinancialTag> getTags() {
        Set<FinancialTag> tags = new HashSet<>();

        this.tags.getChildren().forEach(tag -> {
            if(tag instanceof CheckBox checkBox) {
                if(checkBox.isSelected()) {
                    service.getFilterManager().getGroup().getTags().forEach(t -> {
                        if(t.getName().equals(checkBox.getText())) {
                            tags.add(t);
                        }
                    });
                }
            }
        });

        return tags;
    }

    public void setApplyButton(String text) {
        applyButton.setText(text);
    }
    public void setDescription(String description) {
        this.description.setText(description);
    }
    public void setAmount(String amount) {
        this.amount.setText(amount);
    }
    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }
    public void setAccount(FinancialAccount account) {
        this.account.setValue(account);
    }
    public void setCategory(FinancialCategory category) {
        this.category.setValue(category);
        updateTags();
    }
    private void updateTags() {
        tags.getChildren().clear();
        tags.getChildren().addAll(generateTags(category.getValue()));
    }

    public void setActiveTags(Set<FinancialTag> tags) {
        tags.forEach(t -> {
            this.tags.getChildren().forEach(tag -> {
                if(tag instanceof CheckBox checkBox) {
                    if(checkBox.getText().equals(t.getName()))
                        checkBox.setSelected(true);
                }
            });
        });
    }

    private List<CheckBox> generateTags(FinancialCategory category) {
        List<CheckBox> tags = new ArrayList<>();

        service.getFilterManager().getGroup().getTags()
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

    @FXML
    public void openNewAccountDialog() {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder("NewAccountDialog","New Account");
    }

    @FXML
    public void openNewCategoryDialog() {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.dialogBuilder("NewCategoryDialog","New Category");
    }

    @FXML
    public void openNewTagDialog() {
        if(category.getValue() == null) return;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/COMPONENTS/NewTagDialog.fxml"));

            Parent root = loader.load();
            NewTagDialogController controller = loader.getController();
            controller.setCategory(category.getValue());
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
}

