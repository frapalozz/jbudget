package it.unicam.cs.mpgc.jbudget125914.model.transaction;

import it.unicam.cs.mpgc.jbudget125914.model.category.Category;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class TransactionCategoryId {

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
