package it.unicam.cs.mpgc.jbudget125914.manager;

import it.unicam.cs.mpgc.jbudget125914.currency.Money;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.transaction.TransactionType;

import java.time.LocalDate;
import java.util.List;

public class MoneyAccountManager extends AbstractAccountManager<Double, Money> {

    public MoneyAccountManager(List<Transaction<Money>> transactions, Money balance) {
        super(transactions, balance);
    }

    public Money getBalanceAtDate(LocalDate date) {
        return (Money) this.getBalance().sum(
                new Money(-transactionAfterDate(date)
                .mapToDouble(t -> t.getType() == TransactionType.EXPENSE? t.amount().amount(): -t.amount().amount())
                .sum())
        );
    }
}
