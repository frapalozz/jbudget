package it.unicam.cs.mpgc.jbudget125914;



import it.unicam.cs.mpgc.jbudget125914.categories.DefaultCategory;
import it.unicam.cs.mpgc.jbudget125914.currency.Money;
import it.unicam.cs.mpgc.jbudget125914.manager.MoneyManager;
import it.unicam.cs.mpgc.jbudget125914.manager.TransactionsManager;
import it.unicam.cs.mpgc.jbudget125914.transaction.DefaultTransaction;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;

import java.util.*;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        List<Transaction<Money, DefaultCategory>> transactions = new ArrayList<>();
        Set<DefaultCategory> categories = new HashSet<>();
        categories.add(new DefaultCategory("Health"));
        categories.add(new DefaultCategory("Home"));
        categories.add(new DefaultCategory("Sport"));
        transactions.add(new DefaultTransaction<Money, DefaultCategory>("Abbonamento palestra", new Money(-299.00, "EUR"), new Date(), Set.of(new DefaultCategory("Sport")) ));
        transactions.add(new DefaultTransaction<Money, DefaultCategory>("Stipendio", new Money(1500.00, "EUR"), new Date(), Set.of(new DefaultCategory("Work")) ));
        TransactionsManager moneyManager = new MoneyManager(transactions, categories);

        moneyManager.getTransactions().forEach(System.out::println);

    }
}
