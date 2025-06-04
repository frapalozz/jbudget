package it.unicam.cs.mpgc.jbudget125914.model.entities.category;

import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.FinancialTransaction;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

@Entity
@Immutable
@Getter
@Table(name = "financial_tags")
public final class FinancialTag extends AbstractTag<FinancialTransaction> {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "financial_transaction_tags",
            joinColumns = @JoinColumn(name = "tag_name", table = "financial_tags"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id", table = "financial_transactions")
    )
    private final Set<FinancialTransaction> transactions;

    @ManyToOne
    @JoinColumn(name = "parent")
    private final FinancialTag parent;

    @OneToMany(mappedBy = "parent")
    private final Set<FinancialTag> childrens;

    public FinancialTag(String name, FinancialTag parent, Set<FinancialTag> childrens, Set<FinancialTransaction> transactions) {
        super(name);
        this.parent = parent;
        this.childrens = childrens;
        this.transactions = transactions;
    }

    public FinancialTag() {
        transactions = new HashSet<>();
        this.parent = null;
        this.childrens = new HashSet<>();
    }

    @Override
    public Set<Tag<FinancialTransaction>> getChildrens() {
        return new HashSet<>(childrens);
    }
}
