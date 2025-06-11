package it.unicam.cs.mpgc.jbudget125914.model.tag;

import it.unicam.cs.mpgc.jbudget125914.model.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.transaction.details.AbstractDetail;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractTag<T extends Transaction<? extends AbstractDetail, T, D>, D extends Tag<T, D>> implements Tag<T, D> {

    @Id
    @Column(length = 50)
    private String name;

    public AbstractTag(String name) {
        this.name = name;
    }
}
