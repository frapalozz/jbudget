package it.unicam.cs.mpgc.jbudget125914.view.dialogs;

import it.unicam.cs.mpgc.jbudget125914.view.util.ControllerUtil;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.controller.manager.FinancialServiceManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class NewAccountDialogController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField initialAmount;

    private final FinancialServiceManager service = FinancialServiceManager.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ControllerUtil cUtil = new ControllerUtil();
        cUtil.formatAmount(initialAmount);
    }

    @FXML
    public void apply() {
        FinancialAccount account = new FinancialAccount(
                name.getText(),
                new FinancialAmount(BigDecimal.valueOf(Double.parseDouble(initialAmount.getText()))),
                service.getFilterManager().getGroup()
                );
        service.getGeneralManager().getAccountDAO().create(account);
        service.getFilterManager().getGroup().getAccounts().add(account);
        service.getFetchManager().updateGroup(service.getGeneralManager(), service.getFilterManager());
    }
}
