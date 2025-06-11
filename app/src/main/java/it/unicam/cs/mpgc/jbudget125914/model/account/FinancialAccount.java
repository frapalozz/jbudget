package it.unicam.cs.mpgc.jbudget125914.model.account;

import it.unicam.cs.mpgc.jbudget125914.model.tag.FinancialTag;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.FinancialTransaction;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.details.FinancialDetail;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "financial_account")
public class FinancialAccount extends AbstractAccount<FinancialDetail, FinancialTransaction, FinancialTag> {

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FinancialTransaction> transactions;

    public FinancialAccount(Long id, String name, String description, FinancialDetail initial_details, List<FinancialTransaction> transactions) {
        super(id, name, description, initial_details);
        this.transactions = transactions;
    }

    @Override
    public FinancialDetail getCurrentDetails() {
        return new FinancialDetail(
                transactions.parallelStream()
                        .map(t -> t.getDetails().getAmount())
                        .reduce(this.getInitialDetails().getAmount(), BigDecimal::add),
                this.getInitialDetails().getCurrency()
        );
    }
}
