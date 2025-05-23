package it.unicam.cs.mpgc.jbudget125914;



import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Money;
import it.unicam.cs.mpgc.jbudget125914.manager.BudgetManager;
import it.unicam.cs.mpgc.jbudget125914.manager.Range;
import it.unicam.cs.mpgc.jbudget125914.manager.TransactionsManager;
import it.unicam.cs.mpgc.jbudget125914.transaction.MoneyTransaction;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.time.LocalDate;
import java.util.*;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        /*
        Set<DefaultCategory> categories = new HashSet<>();
        categories.add(new DefaultCategory("Health"));
        categories.add(new DefaultCategory("Home"));
        categories.add(new DefaultCategory("Sport"));
        */
        transactions.add(new MoneyTransaction("Abbonamento palestra", new Money(-299.00, "EUR"), LocalDate.now(), Set.of(new DefaultCategory("Sport")) ));
        transactions.add(new MoneyTransaction("Abbonamento palestra2", new Money(-199.00, "USD"), LocalDate.parse("2025-05-03"), Set.of(new DefaultCategory("Sport")) ));
        transactions.add(new MoneyTransaction("Stipendio", new Money(1500.00, "EUR"), LocalDate.parse("2025-02-15"), Set.of(new DefaultCategory("Work")) ));
        TransactionsManager moneyManager = new BudgetManager(500, Range.WEEK, transactions);

        moneyManager.getTransactions().forEach(System.out::println);

    }
}
