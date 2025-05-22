package it.unicam.cs.mpgc.jbudget125914;



import java.util.*;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        List<Transaction<Money, Category>> transactions = new ArrayList<>();
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("Health", 1));
        categories.add(new Category("Home", 2));
        categories.add(new Category("Sport", 3));
        transactions.add(new MoneyTransaction("Abbonamento palestra", TransactionType.EXPENSE, new Money(299.00, "EUR"), new Date(), Set.of(new Category("Sport", 3)) ));
        TransactionsManager moneyManager = new MoneyManager(transactions, categories);

        moneyManager.getAllTransactions().forEach(System.out::println);

    }
}
