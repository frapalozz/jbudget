package it.unicam.cs.mpgc.jbudget125914.model.entities.category;

import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details.AbstractDetail;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Immutable
@MappedSuperclass
public abstract class AbstractTag<T extends Transaction<? extends AbstractDetail, T>> implements Tag<T> {

    @Id
    @Column(length = 50)
    private final String name;

    public AbstractTag(String name) {
        this.name = name;
    }

    public AbstractTag() {
        this.name = null;
    }

    @Override
    public String toString() {
        return "Tag [name: " + name + "]";
    }
}
