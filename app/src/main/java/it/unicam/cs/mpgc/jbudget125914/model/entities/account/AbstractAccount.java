package it.unicam.cs.mpgc.jbudget125914.model.entities.account;

import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.Transaction;
import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details.AbstractDetail;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Immutable
@MappedSuperclass
public abstract class AbstractAccount<T extends AbstractDetail, D extends Transaction<T, D>> implements Account<T, D> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long account_id;

    @Column(nullable = false, unique = true, length = 50)
    private final String name;

    @Column(columnDefinition = "TEXT")
    private final String description;

    @Embedded
    private final T initial_details;

    public AbstractAccount(Long id, String name, String description, T initial_details) {
        this.account_id = id;
        this.name = name;
        this.description = description;
        this.initial_details = initial_details;
    }

    public AbstractAccount() {
        this.account_id = null;
        this.name = null;
        this.description = null;
        this.initial_details = null;
    }

    @Override
    public String toString() {
        return "Account [ID: " + account_id +
                " | name: " + name +
                " | description: " + description +
                " | balance: " + initial_details + "]";
    }
}
