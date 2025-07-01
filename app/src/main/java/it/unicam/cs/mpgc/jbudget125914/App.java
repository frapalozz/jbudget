package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.models.entities.account.FinancialAccount;
import it.unicam.cs.mpgc.jbudget125914.models.embeddable.amount.FinancialAmount;
import it.unicam.cs.mpgc.jbudget125914.models.entities.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.models.services.AccountService;
import it.unicam.cs.mpgc.jbudget125914.models.services.TransactionService;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {


        TransactionService<FinancialTransaction, FinancialAccount, FinancialTag, BigDecimal, LocalDate ,FinancialAmount> transactionService =
                new TransactionService<>(FinancialTransaction.class);

        AccountService<FinancialAccount, BigDecimal, FinancialAmount> accountService = new AccountService<>(FinancialAccount.class);

            FinancialAmount totalBalance = accountService.accountsInitialBalance(FinancialAmount.class, BigDecimal.class);
        FinancialAmount transactionBalance = transactionService.getTransactionAmount(LocalDate.now(), FinancialAmount.class, BigDecimal.class, null);

        System.out.println("Balance: " + transactionBalance.add(totalBalance));

        /*
        CategoryService<FinancialAmount, FinancialTransaction, FinancialTag, FinancialCategory> service = new CategoryService<>(FinancialCategory.class);
        System.out.println(service.getCategoryBalance(FinancialTransaction.class));


         */


        //List<FinancialTransaction> transactions = transactionController.findAll();
        //transactions.forEach(System.out::println);
    }
}
