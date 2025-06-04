package it.unicam.cs.mpgc.jbudget125914.model.entities.account;

import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details.FinancialDetail;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.util.ArrayList;
import java.util.List;

@Entity
@Immutable
@Getter
@Table(name = "financial_account")
public final class FinancialAccount extends AbstractAccount<FinancialDetail, FinancialTransaction> {

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<FinancialTransaction> transactions;

    public FinancialAccount(Long id, String name, String description, FinancialDetail initial_details, List<FinancialTransaction> transactions) {
        super(id, name, description, initial_details);
        this.transactions = transactions;

    }

    public FinancialAccount() {
        super();
        this.transactions = new ArrayList<>();
    }

    @Override
    public List<Transaction<FinancialDetail, FinancialTransaction>> getTransactions() {
        return new ArrayList<>(transactions);
    }
}
