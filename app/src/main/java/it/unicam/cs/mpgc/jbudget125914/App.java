package it.unicam.cs.mpgc.jbudget125914;

import it.unicam.cs.mpgc.jbudget125914.models.services.TransactionService;
import it.unicam.cs.mpgc.jbudget125914.models.entities.transaction.FinancialTransaction;

import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {
        
        TransactionService<FinancialTransaction> transactionController =
                new TransactionService<>(FinancialTransaction.class);

        List<FinancialTransaction> transactions = transactionController.findAll();
        transactions.forEach(System.out::println);
    }
}
