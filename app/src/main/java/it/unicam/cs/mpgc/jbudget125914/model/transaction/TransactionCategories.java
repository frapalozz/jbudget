package it.unicam.cs.mpgc.jbudget125914.model.transaction;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransactionCategories {

    @EmbeddedId
    private TransactionCategoryId id;
}
