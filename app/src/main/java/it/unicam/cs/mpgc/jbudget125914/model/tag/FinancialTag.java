package it.unicam.cs.mpgc.jbudget125914.model.tag;

import it.unicam.cs.mpgc.jbudget125914.model.transaction.FinancialTransaction;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "financial_tags")
public class FinancialTag extends AbstractTag<FinancialTransaction, FinancialTag> {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "financial_transaction_tags",
            joinColumns = @JoinColumn(name = "tag_name", table = "financial_tags"),
            inverseJoinColumns = @JoinColumn(name = "transactionId", table = "financial_transactions")
    )
    private Set<FinancialTransaction> transactions;

    @ManyToOne
    @JoinColumn(name = "parent")
    private FinancialTag parent;

    @OneToMany(mappedBy = "parent")
    private Set<FinancialTag> childrens;

    public FinancialTag(String name, FinancialTag parent, Set<FinancialTag> childrens, Set<FinancialTransaction> transactions) {
        super(name);
        this.parent = parent;
        this.childrens = childrens;
        this.transactions = transactions;
    }

    @Override
    public Set<FinancialTag> getChildrens() {
        return Set.copyOf(childrens);
    }

    @Override
    public Set<FinancialTag> getTagsSubTree() {
        Set<FinancialTag> tagsSubTree = new HashSet<>();
        tagsSubTree.add(this);
        tagsSubTree.addAll(childrens);
        return tagsSubTree;
    }

}
