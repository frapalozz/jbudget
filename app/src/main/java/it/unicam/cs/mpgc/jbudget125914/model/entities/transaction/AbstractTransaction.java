package it.unicam.cs.mpgc.jbudget125914.model.entities.transaction;

import it.unicam.cs.mpgc.jbudget125914.model.entities.transaction.details.AbstractDetail;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;

@Getter
@Immutable
@MappedSuperclass
public abstract class AbstractTransaction<T extends AbstractDetail, D extends Transaction<T, D>> implements Transaction<T, D>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long transaction_id;

    @Embedded
    private final T details;

    @Column(columnDefinition = "DATE")
    private final LocalDate date;

    @Column(columnDefinition = "TEXT")
    private final String description;


    public AbstractTransaction(Long transactionId, T details, LocalDate date, String description) {
        this.transaction_id = transactionId;
        this.details = details;
        this.date = date;
        this.description = description;
    }

    public AbstractTransaction() {
        this.transaction_id = null;
        this.details = null;
        this.date = null;
        this.description = null;
    }

    @Override
    public String toString() {
        return "[ID: " + transaction_id +
                " | Amount: " + details +
                " | Date: " + date +
                " | Description: " + description +
                " | ";
    }

}
