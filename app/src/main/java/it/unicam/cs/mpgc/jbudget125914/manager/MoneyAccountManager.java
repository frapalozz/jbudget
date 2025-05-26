package it.unicam.cs.mpgc.jbudget125914.manager;

import it.unicam.cs.mpgc.jbudget125914.currency.Euro;
import it.unicam.cs.mpgc.jbudget125914.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.transaction.TransactionType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MoneyAccountManager extends AbstractAccountManager<Euro> {

    public MoneyAccountManager(Map<LocalDate, List<Transaction<Euro>>> transactions, Euro balance) {
        super(transactions, balance);
    }

    public Euro getBalanceAtDate(LocalDate date) {
        return (Euro) this.getBalance().sum(
                new Euro(-transactionAfterDate(date)
                .mapToDouble(t -> t.getType() == TransactionType.EXPENSE? t.amount().amount(): -t.amount().amount())
                .sum())
        );
    }
}
